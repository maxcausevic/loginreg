package com.mcausevic.loginreg.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mcausevic.loginreg.models.User;
import com.mcausevic.loginreg.services.UserService;
import com.mcausevic.loginreg.validator.UserValidator;

@Controller
public class MainController {
	private final UserService userServ;
	private final UserValidator userValidator;
	public MainController(UserService userServ,UserValidator userValidator) {
		this.userServ = userServ;
		this.userValidator = userValidator;
	}
	
@RequestMapping("/")
public String index(Model model) {
	model.addAttribute("user", new User());
	return "index.jsp";
}
@RequestMapping(value="/registration", method=RequestMethod.POST)
public String register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session){
	  userValidator.validate(user, result);
	if (result.hasErrors()) {
		return "index.jsp";
	}else {
		User u = userServ.registerUser(user);
		session.setAttribute("userId", u.getId());
		return "redirect:/";
	}
	
}

}

	


