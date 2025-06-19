package tta.orderCollector.utils;

import tta.fix.message.oeg.FIX5_New_Order_Single;
import tta.fix.message.oeg.FIX5_Order_Cancel_Replace_Request;
import tta.fix.message.oeg.FIX5_Order_Cancel_Request;
import tta.fix.message.oeg.FIX5_Payload;
import tta.fix.type.FIX5_AccountCode_enum;
import tta.fix.type.FIX5_CustOrderCapacity_enum;
import tta.fix.type.FIX5_LastCapacity_enum;
import tta.fix.type.FIX5_OrdType_enum;
import tta.fix.type.FIX5_Side_enum;
import tta.fix.type.FIX5_TimeInForce_enum;
import tta.fix.utils.ConnexionParams;
import tta.fix.utils.Utils;
import tta.orderCollector.dto.enumerable.Catavoir_enum;
import tta.orderCollector.dto.enumerable.Sens_enum;
import tta.orderCollector.dto.enumerable.TypeOperation_enum;
import tta.orderCollector.dto.enumerable.TypePrix_enum;
import tta.orderCollector.dto.enumerable.TypeValidite_enum;
import tta.orderCollector.dto.model.Order;
import tta.orderCollector.utils.OrderCollectorUtils;

public class ConvertToFIX {

	
	public static FIX5_Payload convertToFIX(Order order) {
	
		try {
			if (order.getTypeOperation()==TypeOperation_enum.NEW.value()) {
				return convertToNew_Order_Single(order);
			}
	
			if (order.getTypeOperation()==TypeOperation_enum.MODIFY.value()) {
				return convertToOrder_Cancel_Replace(order);
			}
	
			if (order.getTypeOperation()==TypeOperation_enum.CANCEL.value()) {
				return convertToOrder_Cancel(order);
			}
			return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	private static FIX5_Payload convertToOrder_Cancel(Order order)  throws Exception {
		// TODO Auto-generated method stub
		FIX5_Order_Cancel_Request fix_order = new FIX5_Order_Cancel_Request();
		return null;
	}

	private static FIX5_Payload convertToOrder_Cancel_Replace(Order order) {
		// TODO Auto-generated method stub
		FIX5_Order_Cancel_Replace_Request fix_order = new FIX5_Order_Cancel_Replace_Request();
		return null;
	}

	private static FIX5_New_Order_Single convertToNew_Order_Single(Order order) {
	
	try {
		FIX5_New_Order_Single fix_order = new FIX5_New_Order_Single();

		// set Order Attributes
		fix_order.setTransactTime(Utils.getUTCTimestamp(order.getDateSaisie().toInstant()));
		fix_order.setClOrdID(String.valueOf(order.getReference()));
		fix_order.setOrderQty(String.valueOf(order.getQuantite()));
		fix_order.setDisclosedQtyRandIndicator("0");//NO
		// set Symbol parameters tags
		setSymbol(order, fix_order);
		
		// set Conditional tags
		setAccountCode(order, fix_order);
		setSide(order, fix_order);
		setPrice(order, fix_order);
		setValidity(order, fix_order);
		setLastCapacity(order, fix_order);
		setScope(order, fix_order);
		
		setParametersTrader(order, fix_order);
		setOptionalParameters(order, fix_order);

		return fix_order;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return null;
	}
	}

	private static void setSymbol(Order order, FIX5_New_Order_Single fix_order) {
		
		fix_order.setSecurityID(order.getSecurity().getSecurityID());
		fix_order.setSecurityIDSource(order.getSecurity().getSecurityIDSource());
		fix_order.setEMM(order.getSecurity().getEmm());
		
	}
	
	// 1/ set account code
	private static void setAccountCode(Order order, FIX5_New_Order_Single fix_order) {

		fix_order.setClearingAccount(order.getNumCompte());
		
		if (order.getCatAvoir().equalsIgnoreCase(Catavoir_enum.COMPTE_CLIENT_LIBRE.value()))
			fix_order.setAccountCode(String.valueOf(FIX5_AccountCode_enum.Client.value()));

		if (order.getCatAvoir().equalsIgnoreCase(Catavoir_enum.COMPTE_CLIENT_GERE.value()))
			fix_order.setAccountCode(String.valueOf(FIX5_AccountCode_enum.Managed_Client.value()));

		if (order.getCatAvoir().equalsIgnoreCase(Catavoir_enum.COMPTE_ETRANGER_LIBRE.value()))
			fix_order.setAccountCode(String.valueOf(FIX5_AccountCode_enum.Foreign.value()));

		if (order.getCatAvoir().equalsIgnoreCase(Catavoir_enum.COMPTE_ETRANGER_GERE.value()))
			fix_order.setAccountCode(String.valueOf(FIX5_AccountCode_enum.Managed_Foreign.value()));

		if (order.getCatAvoir().equalsIgnoreCase(Catavoir_enum.COMPTE_PROPRE.value()))
			fix_order.setAccountCode(String.valueOf(FIX5_AccountCode_enum.House.value()));

		if (order.getCatAvoir().equalsIgnoreCase(Catavoir_enum.COMPTE_OPCVM.value()))
			fix_order.setAccountCode(String.valueOf(FIX5_AccountCode_enum.Undertakings_Collective_Investment.value()));

		if (order.getCatAvoir().equalsIgnoreCase(Catavoir_enum.COMPTE_ANIMATEUR.value())) {
			fix_order.setAccountCode(String.valueOf(FIX5_AccountCode_enum.Liquidity_Provider.value()));
			fix_order.setLPRole("1");// 1 = Liquidity Provider or Market Maker

		}
	}

	// 2/ Set SIDES
	private static void setSide(Order order, FIX5_New_Order_Single fix_order) {

		if (order.getSens()==Sens_enum.BUY.value()) {
			fix_order.setSide(String.valueOf(FIX5_Side_enum.Buy.value()));
			fix_order.setNoSides(String.valueOf(FIX5_Side_enum.Buy.value()));
		}
		if (order.getSens()==Sens_enum.SELL.value()) {
			fix_order.setSide(String.valueOf(FIX5_Side_enum.Sell.value()));
			fix_order.setNoSides(String.valueOf(FIX5_Side_enum.Sell.value()));
		}

	}

	// 3/ Set Type price and price
	private static void setPrice(Order order, FIX5_New_Order_Single fix_order) {

		// open price ou ATP
		if(order.getTypePrix()==TypePrix_enum.OPEN_PRICE.value() ||
				order.getTypePrix()==TypePrix_enum.ATP.value()) {
			fix_order.setOrdType(FIX5_OrdType_enum.Market.value());
		}
		
		//LIMIT ORDER
		if(order.getTypePrix()==TypePrix_enum.FIXED_PRICE.value()) {
			fix_order.setOrdType(FIX5_OrdType_enum.Limit.value());
			fix_order.setPrice(OrderCollectorUtils.CurrencyFormat(order.getPrix()));
		}
		
		//STOP LOSS
		if(order.getTypePrix()==TypePrix_enum.STOP_LOSS.value()) {
			fix_order.setOrdType(FIX5_OrdType_enum.Stop_Market_Stop_Market_on_quote.value());
			fix_order.setStopPx(OrderCollectorUtils.CurrencyFormat(order.getPrixStop()));
			
			if(order.getTypeValiditeStop()==TypeValidite_enum.DAY.value()) 
				fix_order.setTriggeredStopTimeInForce(FIX5_TimeInForce_enum.Day.value());
			
			if(order.getTypeValiditeStop()==TypeValidite_enum.NEXT_YEAR.value()) 
				fix_order.setTriggeredStopTimeInForce(FIX5_TimeInForce_enum.GTC.value());
			
			if(order.getTypeValiditeStop()==TypeValidite_enum.FIXED_DATE.value()) 
				fix_order.setTriggeredStopTimeInForce(FIX5_TimeInForce_enum.GTD.value());
			
			if(order.getTypeValiditeStop()==TypeValidite_enum.END_OF_MONTH.value()) 
				fix_order.setTriggeredStopTimeInForce(FIX5_TimeInForce_enum.GTM.value());
			
		}
		
		//STOP LIMIT
		if(order.getTypePrix()==TypePrix_enum.STOP_LIMIT.value()) {
			fix_order.setOrdType(FIX5_OrdType_enum.Stop_limit_Stop_on_quot_limit.value());
			fix_order.setPrice(OrderCollectorUtils.CurrencyFormat(order.getPrix()));
			fix_order.setStopPx(OrderCollectorUtils.CurrencyFormat(order.getPrixStop()));

			if(order.getTypeValiditeStop()==TypeValidite_enum.DAY.value()) 
				fix_order.setTriggeredStopTimeInForce(FIX5_TimeInForce_enum.Day.value());
			
			if(order.getTypeValiditeStop()==TypeValidite_enum.NEXT_YEAR.value()) 
				fix_order.setTriggeredStopTimeInForce(FIX5_TimeInForce_enum.GTC.value());
			
			if(order.getTypeValiditeStop()==TypeValidite_enum.FIXED_DATE.value()) 
				fix_order.setTriggeredStopTimeInForce(FIX5_TimeInForce_enum.GTD.value());
			
			if(order.getTypeValiditeStop()==TypeValidite_enum.END_OF_MONTH.value()) 
				fix_order.setTriggeredStopTimeInForce(FIX5_TimeInForce_enum.GTM.value());
			
		}
		
		if(order.getTypePrix()==TypePrix_enum.BEST_LIMIT.value()) {
			fix_order.setOrdType(FIX5_OrdType_enum.Market_to_limit.value());
		}
		
		/*if(order.getTypePrix().equalsIgnoreCase(TypePrix_enum.Peg.value())) {
			fix_order.setOrdType(FIX5_OrdType_enum.Peg.value());
			
			if(order.getTypePrixPeg().equalsIgnoreCase(TypePrixPeg_enum.Market_peg.value()))
			{
				fix_order.setPegPriceType(String.valueOf(FIX5_PegPriceType_enum.Market_peg.value()));
				fix_order.setPegOffsetValue(String.valueOf(order.getOffsetPeg()));
			}
			if(order.getTypePrixPeg().equalsIgnoreCase(TypePrixPeg_enum.Primary_peg.value()))
			{
				fix_order.setPegPriceType(String.valueOf(FIX5_PegPriceType_enum.Primary_peg.value()));
				fix_order.setPegOffsetValue(String.valueOf(order.getOffsetPeg()));
			}
			if(order.getTypePrixPeg().equalsIgnoreCase(TypePrixPeg_enum.Mid_price_peg.value()))
				fix_order.setPegPriceType(String.valueOf(FIX5_PegPriceType_enum.Mid_price_peg.value()));
			

		}*/
		
		if(order.getQuantiteDev()>0) {
			fix_order.setOrdType(FIX5_OrdType_enum.Iceberg.value());
			fix_order.setPrice(OrderCollectorUtils.CurrencyFormat(order.getPrix()));
			//Indique si le client demande ou non une randomisation pour la quantité de commande d'iceberg (à remplir que pour iceberg order)
			fix_order.setDisclosedQtyRandIndicator("1");//YES
			fix_order.setDisplayQty(String.valueOf(order.getQuantiteDev()));
		}
		
	}
	
	// 4/ Set TimeInForce and order validity
	private static void setValidity(Order order, FIX5_New_Order_Single fix_order) {

		if(order.getTypeValidite()==TypeValidite_enum.DAY.value())
		{
			fix_order.setTimeInForce(FIX5_TimeInForce_enum.Day.value());
		}
		
		if(order.getTypeValidite()==TypeValidite_enum.FOK.value())
		{
			fix_order.setTimeInForce(FIX5_TimeInForce_enum.FOK.value());
		}
		
		if(order.getTypeValidite()==TypeValidite_enum.FIXED_DATE.value())
		{
			fix_order.setTimeInForce(FIX5_TimeInForce_enum.GTD.value());
			fix_order.setExpireTime(OrderCollectorUtils.SDF.format(order.getDateValidite()));
		}
		
		if(order.getTypeValidite()==TypeValidite_enum.END_OF_MONTH.value())
		{
			fix_order.setTimeInForce(FIX5_TimeInForce_enum.GTM.value());
		}

		if(order.getTypeValidite()==TypeValidite_enum.NEXT_YEAR.value())
		{
			fix_order.setTimeInForce(FIX5_TimeInForce_enum.GTC.value());
		}
		/*		
		if(order.getTypeValidite().equals(TypeValidite_enum.Immediate_or_Cancel.value()))
		{
			fix_order.setTimeInForce(FIX5_TimeInForce_enum.IOC.value());
		}
		
		if(order.getTypeValidite().equals(TypeValidite_enum.At_the_Close.value()))
		{
			fix_order.setTimeInForce(FIX5_TimeInForce_enum.At_the_Close.value());
		}
		
		if(order.getTypeValidite().equals(TypeValidite_enum.Good_for_Time.value()))
		{
			fix_order.setTimeInForce(FIX5_TimeInForce_enum.GTT.value());
			fix_order.setExpireTime(order.getDateValidite());
		}
		
		if(order.getTypeValidite().equals(TypeValidite_enum.Good_for_auction.value()))
		{
			fix_order.setTimeInForce(FIX5_TimeInForce_enum.GFA.value());
		}
		*/
	}

	// 5/ Set LastCapacity
	private static void setLastCapacity(Order order, FIX5_New_Order_Single fix_order) {
		//In inbound messages this field can be provided with any of the allowed values, they are not used. 
		fix_order.setLastCapacity(String.valueOf(FIX5_LastCapacity_enum.Matched_principal.value()));

	}
		
	// 6/ Set Scope Order
	private static void setScope(Order order, FIX5_New_Order_Single fix_order) {
		
		//set this fild to 1 for SBT else it must be sent by the trader with order
		//fix_order.setCancelOnDisconnectionIndicator(order.getScope());
		fix_order.setCancelOnDisconnectionIndicator("1");
			
	}

	// 7/ Set Parameters trader
	private static void setParametersTrader(Order order, FIX5_New_Order_Single fix_order) {
					
		fix_order.setNoPartyIDs("1");// If provided, always set to 1
		fix_order.setPartyID(ConnexionParams.PartyID);// Mandatory if NoPartyIDs >= 1
		fix_order.setPartyIDSource("C");// C (Generally accepted market participant identifier), Mandatory if NoPartyIDs >= 1
		fix_order.setPartyRole("36");// 7 = Entering Firm 36 = Entering Trader ,Mandatory if NoPartyIDs >= 1.

//		fix_order.setNoNestedPartyIDs("1");//If provided, from 1 to 3
//		fix_order.setNestedPartyID(NestedPartyID);//Client ID or Investor ID or ID of clearing firm ID , Mandatory if NoNestedPartyIDs >= 1. 
//		fix_order.setNestedPartyIDSource("C");//C (Generally accepted market participant identifier),Mandatory if NoNestedPartyIDs >= 1. 
//		fix_order.setNestedPartyRole("4");// 5 (Investor ID) ou 3 (Client ID) ou 4 (Firm ID) Mandatory if NoNestedPartyIDs >= 1. 
			
				
	}

	// 8/ Set Optional tags
	private static void setOptionalParameters(Order order, FIX5_New_Order_Single fix_order) {
		
		//fix_order.setSelfMatchPreventionID(SelfMatchPreventionID);
		fix_order.setMinQty(String.valueOf(order.getQuantiteMin()));
		//fix_order.setClearingInstruction(ClearingInstruction);
		fix_order.setText(order.getText());		
		//fix_order.setTechnicalOrdType(TechnicalOrdType);
		fix_order.setPostingAction("1");
		fix_order.setCustOrderCapacity(String.valueOf(FIX5_CustOrderCapacity_enum.For_own_account.value()));
	}
	
}