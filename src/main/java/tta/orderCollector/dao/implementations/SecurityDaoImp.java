package tta.orderCollector.dao.implementations;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tta.orderCollector.dao.interfaces.SecurityDao;
import tta.orderCollector.dto.model.Security;

@Repository
public class SecurityDaoImp implements SecurityDao {

	@Autowired
	private SessionFactory sessionFactory;
	 
	@Override
	public void save(Security security) {
		// TODO Auto-generated method stub
		 sessionFactory.getCurrentSession().save(security);
	}

	@Override
	public Security getByIsin(String isin) {
		// TODO Auto-generated method stub
		 return (Security) sessionFactory.getCurrentSession().get(Security.class, isin);
	}
	
	@Override
	public List<Security> list() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
	      TypedQuery<Security> query = sessionFactory.getCurrentSession().createQuery("from Security");
	      return query.getResultList();
	}

	

}
