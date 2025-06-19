package tta.orderCollector.service.interfaces;

import java.util.List;

import tta.orderCollector.dto.model.Order;

public interface OrderService {

	void save(Order order);
	Order getByReference(long reference);
	Order getByRefOrigine(long refOrigine);
	List<Order> list();

}
