package tta.orderCollector.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tta.orderCollector.dao.interfaces.OrderDao;
import tta.orderCollector.dto.model.Order;
import tta.orderCollector.service.interfaces.OrderService;

@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	  private OrderDao orderDao;
	 
	
	@Override
	@Transactional
	public void save(Order order) {
		// TODO Auto-generated method stub
		orderDao.save(order);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Order> list() {
		// TODO Auto-generated method stub
		return orderDao.list();
	}

	@Override
	@Transactional(readOnly = true)
	public Order getByReference(long reference) {
		// TODO Auto-generated method stub
		return orderDao.getByReference(reference);
	}

	@Override
	@Transactional(readOnly = true)
	public Order getByRefOrigine(long refOrigine) {
		// TODO Auto-generated method stub
		return orderDao.getByRefOrigine(refOrigine);
	}

}
