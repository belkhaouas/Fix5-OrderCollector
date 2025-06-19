package tta.orderCollector.tasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import tta.fix.Session;
import tta.fix.message.oeg.FIX5_Execution_Report;
import tta.fix.message.oeg.FIX5_Heartbeat;
import tta.fix.message.oeg.FIX5_Instrument_Synchronisation_List;
import tta.fix.message.oeg.FIX5_Logon;
import tta.fix.message.oeg.FIX5_Logout;
import tta.fix.message.oeg.FIX5_OpticError;
import tta.fix.message.oeg.FIX5_Order_Cancel_Reject;
import tta.fix.message.oeg.FIX5_Payload;
import tta.fix.message.oeg.FIX5_Reject;
import tta.fix.type.FIX5_Errors_Glossary;
import tta.fix.type.FIX5_SessionStatus_enum;
import tta.fix.utils.Utils;
import tta.orderCollector.dto.Referentiel;
import tta.orderCollector.dto.model.Security;
import tta.orderCollector.service.interfaces.SecurityService;
import tta.orderCollector.utils.OrderCollectorUtils;


public class FixRouter extends Session implements Runnable {

	@Autowired
	SecurityService securityService;

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	private BufferedReader readerFile() {
		try {
			return new BufferedReader(
				new FileReader(OrderCollectorUtils.ordersPath + OrderCollectorUtils.SDF.format(new java.util.Date()) + ".fix"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void onInstrumentMsg(FIX5_Instrument_Synchronisation_List instrumentList) {

		// call ws and get all instruments
		Security security = null;
		
		//String rest = OrderCollectorUtils.restTemplate().getForObject(OrderCollectorUtils.urlReferentiel, String.class);
		
		String rest = new RestTemplate().getForObject(OrderCollectorUtils.urlReferentiel, String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			List<Referentiel> refs = objectMapper.readValue(rest, new TypeReference<List<Referentiel>>() {
			});
			for (int i = 0; i < Integer.parseInt(instrumentList.getNoRelatedSym()); i++) {
				security = new Security();
				security.setEmm(instrumentList.getEMM().get(i));
				security.setSecurityID(instrumentList.getSecurityID().get(i));
				security.setSecurityIDSource(instrumentList.getSecurityIDSource().get(i));
				int pos = getPosBySymbolIndex(instrumentList.getSecurityID().get(i), refs);
				security.setIsin(refs.get(pos).getIsin());
				security.setStockName(refs.get(pos).getStock_NAME());
				securityService.save(security);
			}

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private int getPosBySymbolIndex(String symbolIndex, List<Referentiel> refs) {

		int pos = -1;
		for (int i = 0; i < refs.size(); i++) {

			if (symbolIndex.equals(String.valueOf(refs.get(i).getSymbolindex()))) {
				pos = i;
				break;
			}

		}
		return pos;
	}

	@Override
	public void onSessionMsg(FIX5_Payload message) {

		if (message == null) {
			closeSessionOEG(String.valueOf(FIX5_SessionStatus_enum.System_unavailable.value()));
		}
		if (message instanceof FIX5_Logon) {
		}

		if (message instanceof FIX5_Logout) {
			FIX5_Logout logOut = (FIX5_Logout) message;
			closeSessionOEG(logOut.getSessionStatus());
		}

		if (message instanceof FIX5_Heartbeat) {
		}

		if (message instanceof FIX5_Reject) {
			FIX5_Reject reject = (FIX5_Reject) message;
			if (reject.getSessionRejectReason().equals("1")) {
				closeSessionOEG(String.valueOf(FIX5_SessionStatus_enum.Invalid_Logon_Value.value()));
				}
		}

	}

	@Override
	public void onErrorMsg(FIX5_Payload message) {

		if (message instanceof FIX5_Reject) {
			FIX5_Reject reject = (FIX5_Reject) message;
			if (reject.getSessionRejectReason().equals("1")) {
				closeSessionOEG(String.valueOf(FIX5_SessionStatus_enum.Invalid_Logon_Value.value()));
			}
		}

	}

	@Override
	public void onReportMsg(FIX5_Payload message) {

		if (message instanceof FIX5_Execution_Report) {
			FIX5_Execution_Report execReport = (FIX5_Execution_Report) message;
			FIX5_OpticError error = FIX5_Errors_Glossary.error(Integer.parseInt(execReport.getErrorCode()));
			Utils.writeLog("ERROR", "Rejected order reason :" + error.toString());

		}
		if (message instanceof FIX5_Order_Cancel_Reject) {
			FIX5_Order_Cancel_Reject execReport = (FIX5_Order_Cancel_Reject) message;

		}

		// order.setStatut(Statut_enum.ANY.value());
		// 2 si ok : enregistrer l'ordre dans la base de données avec flag TTA
		// orderService.save(order);
		// et l'envoyer à SBT

	}

	@Override
	public void onResponseMsg(String msg) {

		try {
			sendMessage(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
