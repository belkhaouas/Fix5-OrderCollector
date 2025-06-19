package tta.orderCollector.controller.rest;


import java.util.ArrayList;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tta.orderCollector.dto.*;
import tta.orderCollector.dto.enumerable.Catavoir_enum;
import tta.orderCollector.dto.enumerable.Scope_enum;
import tta.orderCollector.dto.enumerable.Sens_enum;
import tta.orderCollector.dto.enumerable.Statut_enum;
import tta.orderCollector.dto.enumerable.TypeOperation_enum;
import tta.orderCollector.dto.enumerable.TypePrix_enum;
import tta.orderCollector.dto.enumerable.TypeValidite_enum;
import tta.orderCollector.dto.model.Order;
import tta.orderCollector.dto.model.Security;

@RestController
@RequestMapping("/documentation") 
public class DocsController {
	
	@GetMapping("/order")
	public ResponseObject order() {

		ResponseObject responseObject = new ResponseObject();
		ArrayList<Attribut> elements= new ArrayList<Attribut>();
		Attribut attribut;

		for(int i=0; i<Order.class.getDeclaredFields().length ; i++) {
			
			attribut= new Attribut();
			attribut.setAttribute(Order.class.getDeclaredFields()[i].getName().toString());;
			attribut.setType(Order.class.getDeclaredFields()[i].getType().toString());;
			elements.add(attribut);
			
		}
		
		responseObject.setElements(elements);
		return responseObject;
	}

	@GetMapping("/security")
	public ResponseObject security() {
		ResponseObject responseObject = new ResponseObject();
		ArrayList<Attribut> elements= new ArrayList<Attribut>();
		Attribut attribut;

		for(int i=0; i<Security.class.getDeclaredFields().length ; i++) {

			attribut= new Attribut();
			attribut.setAttribute(Security.class.getDeclaredFields()[i].getName().toString());;
			attribut.setType(Security.class.getDeclaredFields()[i].getType().toString());;
			elements.add(attribut);
		}
		responseObject.setElements(elements);
		return responseObject;
	}
	
	@GetMapping("/catavoir")
	public ResponseObject catavoir_enum() {
		ResponseObject responseObject = new ResponseObject();
		ArrayList<Attribut> elements= new ArrayList<Attribut>();
		Attribut attribut;

		for(int i=0; i<Catavoir_enum.class.getEnumConstants().length ; i++)
		{
			attribut= new Attribut();
			attribut.setAttribute(Catavoir_enum.class.getEnumConstants()[i].name());;
			attribut.setType(Catavoir_enum.class.getEnumConstants()[i].value());;
			elements.add(attribut);
			
		}
		responseObject.setElements(elements);
		return responseObject;		
	}
	
	@GetMapping("/scope")
	public ResponseObject scope_enum() {
		ResponseObject responseObject = new ResponseObject();
		ArrayList<Attribut> elements= new ArrayList<Attribut>();
		Attribut attribut;

		for(int i=0; i<Scope_enum.class.getEnumConstants().length ; i++)
		{

			attribut= new Attribut();
			attribut.setAttribute(Scope_enum.class.getEnumConstants()[i].name());;
			attribut.setType(Scope_enum.class.getEnumConstants()[i].value());;
			elements.add(attribut);
			
		}
		responseObject.setElements(elements);
		return responseObject;
	}
	
	@GetMapping("/sens")
	public ResponseObject sens_enum() {
		ResponseObject responseObject = new ResponseObject();
		ArrayList<Attribut> elements= new ArrayList<Attribut>();
		Attribut attribut;

		for(int i=0; i<Sens_enum.class.getEnumConstants().length ; i++)
		{

			attribut= new Attribut();
			attribut.setAttribute(Sens_enum.class.getEnumConstants()[i].name());;
			attribut.setType(String.valueOf(Sens_enum.class.getEnumConstants()[i].value()));
			elements.add(attribut);
			
		}
		responseObject.setElements(elements);
		return responseObject;	
	}
	
	@GetMapping("/typeOperation")
	public ResponseObject typeOperation_enum() {
		ResponseObject responseObject = new ResponseObject();
		ArrayList<Attribut> elements= new ArrayList<Attribut>();
		Attribut attribut;

		for(int i=0; i<TypeOperation_enum.class.getEnumConstants().length ; i++)
		{

			attribut= new Attribut();
			attribut.setAttribute(TypeOperation_enum.class.getEnumConstants()[i].name());;
			attribut.setType(String.valueOf(TypeOperation_enum.class.getEnumConstants()[i].value()));
			elements.add(attribut);
			
		}
		responseObject.setElements(elements);
		return responseObject;	
	}
	
	
	@GetMapping("/typePrix")
	public ResponseObject typePrix_enum() {
		ResponseObject responseObject = new ResponseObject();
		ArrayList<Attribut> elements= new ArrayList<Attribut>();
		Attribut attribut;

		for(int i=0; i<TypePrix_enum.class.getEnumConstants().length ; i++)
		{

			attribut= new Attribut();
			attribut.setAttribute(TypePrix_enum.class.getEnumConstants()[i].name());;
			attribut.setType(String.valueOf(TypePrix_enum.class.getEnumConstants()[i].value()));
			elements.add(attribut);
			
		}
		responseObject.setElements(elements);
		return responseObject;		
	}
	
	@GetMapping("/typeStatut")
	public ResponseObject typeStatut_enum() {
		ResponseObject responseObject = new ResponseObject();
		ArrayList<Attribut> elements= new ArrayList<Attribut>();
		Attribut attribut;

		for(int i=0; i<Statut_enum.class.getEnumConstants().length ; i++)
		{

			attribut= new Attribut();
			attribut.setAttribute(Statut_enum.class.getEnumConstants()[i].name());;
			attribut.setType(String.valueOf(Statut_enum.class.getEnumConstants()[i].value()));
			elements.add(attribut);
			
			
		}
		responseObject.setElements(elements);
		return responseObject;	
	}
	
	
	@GetMapping("/typeValidite")
	public ResponseObject typeValidite_enum() {
		ResponseObject responseObject = new ResponseObject();
		ArrayList<Attribut> elements= new ArrayList<Attribut>();
		Attribut attribut;

		for(int i=0; i<TypeValidite_enum.class.getEnumConstants().length ; i++)
		{

			attribut= new Attribut();
			attribut.setAttribute(TypeValidite_enum.class.getEnumConstants()[i].name());;
			attribut.setType(String.valueOf(TypeValidite_enum.class.getEnumConstants()[i].value()));
			elements.add(attribut);
			
		}
		responseObject.setElements(elements);
		return responseObject;
	}
	
	
	
}
