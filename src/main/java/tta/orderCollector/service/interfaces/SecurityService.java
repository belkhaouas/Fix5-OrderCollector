package tta.orderCollector.service.interfaces;

import java.util.List;

import tta.orderCollector.dto.model.Security;

public interface SecurityService {

	void save(Security security);
	Security getByIsin(String isin);
	List<Security> list();

}
