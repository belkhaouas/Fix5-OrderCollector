package tta.orderCollector.dto.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "SECURITIES")
public class Security {

	@Id
	@Column(name = "ISIN")
	private String isin;
	
	@Column(name = "STOCKNAME")
	private String stockName;
	
	@Column(name = "ID")
	private String securityID;
	
	@Column(name = "IDSOURCE")
	private String securityIDSource;
	
	@Column(name = "EMM")
	private String emm;
	
	 @OneToMany(mappedBy="security")
     private Collection<Order> orders ;
	 
	public String getIsin() {
		return isin;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	public String getSecurityID() {
		return securityID;
	}
	public void setSecurityID(String securityID) {
		this.securityID = securityID;
	}
	public String getSecurityIDSource() {
		return securityIDSource;
	}
	public void setSecurityIDSource(String securityIDSource) {
		this.securityIDSource = securityIDSource;
	}
	public String getEmm() {
		return emm;
	}
	public void setEmm(String emm) {
		this.emm = emm;
	}
	public Collection<Order> getOrders() {
		return orders;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Security [isin=").append(isin).append(", stockName=").append(stockName).append(", securityID=")
				.append(securityID).append(", securityIDSource=").append(securityIDSource).append(", emm=").append(emm)
				.append("]");
		return builder.toString();
	}
	
	
	
	
}
