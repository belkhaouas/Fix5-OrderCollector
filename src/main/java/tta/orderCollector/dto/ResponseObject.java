package tta.orderCollector.dto;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ResponseObject {

	private ArrayList<Attribut> elements;

	public ArrayList<Attribut> getElements() {
		return elements;
	}

	public void setElements(ArrayList<Attribut> elements) {
		this.elements = elements;
	}

	
	
	
}
