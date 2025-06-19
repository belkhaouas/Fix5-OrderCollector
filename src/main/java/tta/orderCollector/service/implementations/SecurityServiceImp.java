package tta.orderCollector.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tta.orderCollector.dao.interfaces.SecurityDao;
import tta.orderCollector.dto.model.Security;
import tta.orderCollector.service.interfaces.SecurityService;

@Service
public class SecurityServiceImp implements SecurityService {

	@Autowired
	  private SecurityDao securityDao;
	 
	
	@Override
	@Transactional
	public void save(Security security) {
		// TODO Auto-generated method stub
		 securityDao.save(security);
	}
	
	@Override
	public Security getByIsin(String isin) {
		// TODO Auto-generated method stub
		return securityDao.getByIsin(isin);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Security> list() {
		// TODO Auto-generated method stub
		return securityDao.list();
	}

	
	 
}
