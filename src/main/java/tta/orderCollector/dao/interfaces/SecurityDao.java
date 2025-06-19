package tta.orderCollector.dao.interfaces;

import java.util.List;

import tta.orderCollector.dto.model.Security;

public interface SecurityDao {

	void save(Security security);
	Security getByIsin(String isin);
	List<Security> list();
}
