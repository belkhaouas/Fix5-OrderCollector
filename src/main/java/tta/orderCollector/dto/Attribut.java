package tta.orderCollector.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Attribut {

	private String attribute;
	private String type;
	
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
