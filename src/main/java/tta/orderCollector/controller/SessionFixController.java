package tta.orderCollector.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/sessionFix")
public class SessionFixController {

	@GetMapping("")
	public String connect(Model model) {
		
		
		model.addAttribute("SessionOEG",  String.valueOf(0));
		return "session";
	}

	
}
