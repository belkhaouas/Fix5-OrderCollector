package tta.orderCollector.dao.implementations;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tta.orderCollector.dao.interfaces.OrderDao;
import tta.orderCollector.dto.model.Order;

@Repository
public class OrderDaoImp implements OrderDao {

	@Autowired
	private SessionFactory sessionFactory;
	 
	@Override
	public void save(Order order) {
		// TODO Auto-generated method stub
		 sessionFactory.getCurrentSession().save(order);
	}

	@Override
	public Order getByReference(long reference) {
		// TODO Auto-generated method stub
        return (Order) sessionFactory.getCurrentSession().get(Order.class, reference);
    
	}
	
	
	@Override
	public Order getByRefOrigine(long refOrigine) {
		// TODO Auto-generated method stub
		Query<Order> query= sessionFactory.getCurrentSession().createNamedQuery("getByReference", 
				Order.class);
		query.setParameter("reference", refOrigine);
		return query.getSingleResult();
		
	}

	@Override
	public List<Order> list() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
	      TypedQuery<Order> query = sessionFactory.getCurrentSession().createQuery("from Order");
	      return query.getResultList();
	}


}
