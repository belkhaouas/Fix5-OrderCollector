package tta.orderCollector.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Referentiel {

	private long symbolindex;
	private long pricepattern;
	private String isin;
	private String cidxpascotvarval;
	private String icbcode;
	private String group;
	private String qpascotfxeval;
	private String pemis;
	private String ticker;
	private String stock_NAME;
	private long amountpattern;
	private long quantitypattern;
	private long ratiopattern;
	public long getSymbolindex() {
		return symbolindex;
	}
	public void setSymbolindex(long symbolindex) {
		this.symbolindex = symbolindex;
	}
	public long getPricepattern() {
		return pricepattern;
	}
	public void setPricepattern(long pricepattern) {
		this.pricepattern = pricepattern;
	}
	public String getIsin() {
		return isin;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	public String getCidxpascotvarval() {
		return cidxpascotvarval;
	}
	public void setCidxpascotvarval(String cidxpascotvarval) {
		this.cidxpascotvarval = cidxpascotvarval;
	}
	public String getIcbcode() {
		return icbcode;
	}
	public void setIcbcode(String icbcode) {
		this.icbcode = icbcode;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getQpascotfxeval() {
		return qpascotfxeval;
	}
	public void setQpascotfxeval(String qpascotfxeval) {
		this.qpascotfxeval = qpascotfxeval;
	}
	public String getPemis() {
		return pemis;
	}
	public void setPemis(String pemis) {
		this.pemis = pemis;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getStock_NAME() {
		return stock_NAME;
	}
	public void setStock_NAME(String stock_NAME) {
		this.stock_NAME = stock_NAME;
	}
	public long getAmountpattern() {
		return amountpattern;
	}
	public void setAmountpattern(long amountpattern) {
		this.amountpattern = amountpattern;
	}
	public long getQuantitypattern() {
		return quantitypattern;
	}
	public void setQuantitypattern(long quantitypattern) {
		this.quantitypattern = quantitypattern;
	}
	public long getRatiopattern() {
		return ratiopattern;
	}
	public void setRatiopattern(long ratiopattern) {
		this.ratiopattern = ratiopattern;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Referentiel [symbolindex=").append(symbolindex).append(", pricepattern=").append(pricepattern)
				.append(", isin=").append(isin).append(", cidxpascotvarval=").append(cidxpascotvarval)
				.append(", icbcode=").append(icbcode).append(", group=").append(group).append(", qpascotfxeval=")
				.append(qpascotfxeval).append(", pemis=").append(pemis).append(", ticker=").append(ticker)
				.append(", stock_NAME=").append(stock_NAME).append(", amountpattern=").append(amountpattern)
				.append(", quantitypattern=").append(quantitypattern).append(", ratiopattern=").append(ratiopattern)
				.append("]");
		return builder.toString();
	}
	
	
}
