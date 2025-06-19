package tta.orderCollector.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tta.orderCollector.service.interfaces.SecurityService;

@Controller
@RequestMapping("/securities")
public class SecurityController {

	@Autowired
	private SecurityService securityService;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String listSecurity(ModelMap model) {
		model.addAttribute("securities", securityService.list());
		return "securities";
	}


}
