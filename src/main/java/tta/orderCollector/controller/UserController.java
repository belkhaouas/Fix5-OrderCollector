package tta.orderCollector.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tta.orderCollector.dto.model.User;
import tta.orderCollector.service.interfaces.UserService;

@Controller
@RequestMapping("/user") 
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String userForm(Locale locale, Model model) {
		model.addAttribute("users", userService.list());
		return "editUsers";
	}
	
	@ModelAttribute("user")
    public User formBackingObject() {
        return new User();
    }

	@PostMapping("/addUser")
	public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("users", userService.list());
			return "editUsers";
		}

		userService.save(user);
		return "redirect:/user/";
	}
}
