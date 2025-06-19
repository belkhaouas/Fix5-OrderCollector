package tta.orderCollector.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tta.orderCollector.service.interfaces.OrderService;
import tta.orderCollector.service.interfaces.SecurityService;

@Controller
@RequestMapping("/")
public class OrderController {

	@Autowired
	private OrderService orderService;

	 @RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String listOrders(ModelMap model) {
		model.addAttribute("orders", orderService.list());
		return "home";
	}
	

}
