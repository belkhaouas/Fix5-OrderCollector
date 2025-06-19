package tta.orderCollector.controller.rest;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tta.fix.message.FIX5_MessageFactory;
import tta.orderCollector.dto.enumerable.Catavoir_enum;
import tta.orderCollector.dto.enumerable.Response_enum;
import tta.orderCollector.dto.enumerable.Sens_enum;
import tta.orderCollector.dto.enumerable.Statut_enum;
import tta.orderCollector.dto.enumerable.TypeOperation_enum;
import tta.orderCollector.dto.enumerable.TypePrix_enum;
import tta.orderCollector.dto.enumerable.TypeValidite_enum;
import tta.orderCollector.dto.model.Order;
import tta.orderCollector.service.interfaces.OrderService;
import tta.orderCollector.service.interfaces.SecurityService;
import tta.orderCollector.utils.ConvertToFIX;
import tta.orderCollector.utils.OrderCollectorUtils;

@RestController
@RequestMapping("/order")
public class ProcessOrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	SecurityService securityService;

	@PostMapping(value = "/sendToTTA", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String getFromSBT(@RequestBody Order order) {

		// 1 controller l'integrité de l'ordre
		int res = controlOrder(order);
		RandomAccessFile witerFile= writerFile();
		if (res == Response_enum.OK.value()) {
			try {
				// 2 si ok : enregistrer l'ordre dans la base de données avec flag TTA
				order.setSecurity(securityService.getByIsin(order.getSecurity().getIsin()));
				order.setStatut(Statut_enum.ANY.value());
				orderService.save(order);

				// et l'ecrire en format fix dans le fichier en ecoute
				String msgFix = FIX5_MessageFactory.appendTo(ConvertToFIX.convertToFIX(order));
				witerFile.seek(witerFile.length());
				witerFile.writeBytes(msgFix + "\n");

				// orderService.commit();
				// 3 renvoyer le resultat à la SBT
				return String.valueOf(Response_enum.OK.value());
			} catch (Exception e) {
				// orderService.rollBack();
				e.printStackTrace();
				return String.valueOf(Response_enum.ORDER_SAVING_ERROR.value());
			}

		} else
			return String.valueOf(res);
	}

	// This function converts Order to FIXOrder (FIX42Message)
	private int controlOrder(Order order) {

		Order ord = orderService.getByReference(order.getReference());
		if (ord != null)
			return Response_enum.ORDER_REFERENCE_ALREADY_RECEIVED.value();

		try {
			TypeOperation_enum.get(order.getTypeOperation());
		} catch (IllegalArgumentException e) {
			return Response_enum.ORDER_OPERATION_TYPE_UNSUPPORTED_VALUE.value();
		}
		try {
			Sens_enum.get(order.getSens());
		} catch (IllegalArgumentException e) {
			return Response_enum.ORDER_SENSE_UNSUPPORTED_VALUE.value();
		}
		try {
			Catavoir_enum.get(order.getCatAvoir());
		} catch (IllegalArgumentException e) {
			return Response_enum.ORDER_ORIGIN_UNSUPPORTED_VALUE.value();
		}
		try {
			TypePrix_enum.get(order.getTypePrix());
		} catch (IllegalArgumentException e) {
			return Response_enum.ORDER_PRICE_TYPE_UNSUPPORTED_VALUE.value();
		}
		try {
			TypeValidite_enum.get(order.getTypeValidite());
		} catch (IllegalArgumentException e) {
			return Response_enum.ORDER_VALIDITY_TYPE_UNSUPPORTED_VALUE.value();
		}

		try {
			TypeValidite_enum.get(order.getTypeValidite());
		} catch (IllegalArgumentException e) {
			return Response_enum.ORDER_VALIDITY_TYPE_UNSUPPORTED_VALUE.value();
		}

		if (order.getSecurity().getIsin().length() != 12)
			return Response_enum.ORDER_ISIN_UNSUPPORTED_VALUE.value();

		if (order.getQuantite() < 0)
			return Response_enum.ORDER_QUANTITY_UNSUPPORTED_VALUE.value();
		if ((order.getQuantite() < order.getQuantiteMin()) || ((order.getTypePrix() == TypePrix_enum.ATP.value()
				|| order.getTypePrix() == TypePrix_enum.OPEN_PRICE.value()) && (order.getQuantiteMin() != -1)))
			return Response_enum.ORDER_MINQUANTITY_NON_PERMITTED.value();

		if (order.getQuantiteDev() != -1) {
			if ((order.getTypePrix() == TypePrix_enum.BEST_LIMIT.value()
					|| order.getTypePrix() == TypePrix_enum.OPEN_PRICE.value()))
				return Response_enum.ORDER_VQUANTITY_NON_PERMITTED.value();

			if (order.getQuantiteDev() < 5 || order.getQuantiteDev() > order.getQuantite())
				return Response_enum.ORDER_VQUANTITY_NON_PERMITTED.value();

		}

		if (order.getDateValidite().before(Calendar.getInstance().getTime()))
			return Response_enum.ORDER_VALIDITY_UNSUPPORTED_VALUE.value();

		// verifier combinaison type prix ,prix et prix declanchement
		if (!((order.getTypePrix() == TypePrix_enum.FIXED_PRICE.value() && order.getPrix() > 0
				&& order.getPrixStop() == -1)
				|| (order.getTypePrix() == TypePrix_enum.STOP_LIMIT.value() && order.getPrix() > 0
						&& order.getPrixStop() > 0)
				|| (order.getTypePrix() == TypePrix_enum.STOP_LOSS.value() && order.getPrix() == -1
						&& order.getPrixStop() > 0)
				|| ((order.getTypePrix() == TypePrix_enum.OPEN_PRICE.value()
						|| order.getTypePrix() == TypePrix_enum.ATP.value()
						|| order.getTypePrix() == TypePrix_enum.BEST_LIMIT.value()
						|| order.getTypePrix() == TypePrix_enum.APPLICATION.value()
				/* || order.getTypePrix()==TypePrix_enum.LAST_PRICE.value() */) && order.getPrix() == -1
						&& order.getPrixStop() == -1)))
			return Response_enum.ORDER_PRICES_NON_PERMITTED.value();

		if (!((order.getTypeOperation() == TypeOperation_enum.NEW.value() && order.getReferenceOrigin() == -1)
				|| ((order.getTypeOperation() == TypeOperation_enum.MODIFY.value()
						|| order.getTypeOperation() == TypeOperation_enum.CANCEL.value())
						&& order.getReferenceOrigin() != -1)))
			return Response_enum.ORDER_REFORIGIN_UNSUPPORTED_VALUE.value();

		if (order.getTypeOperation() == TypeOperation_enum.MODIFY.value()
				|| order.getTypeOperation() == TypeOperation_enum.CANCEL.value()) {
			Order oldOrder = orderService.getByReference(order.getReferenceOrigin());
			if (oldOrder == null)

				return Response_enum.ORDER_REFORIGIN_UNSUPPORTED_VALUE.value();
			else {
				if (oldOrder.getStatut() == 3 || oldOrder.getStatut() == 4 || oldOrder.getStatut() == 6
						|| oldOrder.getStatut() == 9)
					return Response_enum.ORDER_UNSET.value();
				if (order.getSens() != oldOrder.getSens())
					return Response_enum.ORDER_SENSE_UNSET.value();
				if (!order.getSecurity().getIsin().equals(oldOrder.getSecurity().getIsin()))
					return Response_enum.ORDER_ISIN_UNSET.value();
				if (!order.getCatAvoir().equals(oldOrder.getCatAvoir()))
					return Response_enum.ORDER_ORIGINE_UNSET.value();
			}
		}

		return Response_enum.OK.value();
	}

	private RandomAccessFile writerFile() {
		try {
			return new RandomAccessFile(
					new File(OrderCollectorUtils.ordersPath + OrderCollectorUtils.SDF.format(new java.util.Date()) + ".fix"), "rws");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
