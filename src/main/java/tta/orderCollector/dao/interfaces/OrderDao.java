package tta.orderCollector.dao.interfaces;

import java.util.List;

import tta.orderCollector.dto.model.Order;

public interface OrderDao {

	void save(Order order);
	Order getByReference(long reference);
	Order getByRefOrigine(long refOrigine);
	List<Order> list();

}
