package com.mcausevic.loginreg.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
public String register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session,RedirectAttributes redirect){
	  userValidator.validate(user, result);
	if (result.hasErrors()) {
		return "index.jsp";
	}else {
		User u = userServ.registerUser(user);
		session.setAttribute("userId", u.getId());
		redirect.addFlashAttribute("success", "You have successfully registered!");

		return "redirect:/dashboard";
	}
}
@RequestMapping(value="/login", method=RequestMethod.POST)
public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model Model, HttpSession session, RedirectAttributes redirect) {
	if (userServ.authenticateUser(email, password)) {
		User u = userServ.findByEmail(email);
		session.setAttribute("userId", u.getId());
		redirect.addFlashAttribute("success", "You have successfully logged in!");
		return "redirect:/dashboard";
	}else {
		redirect.addFlashAttribute("error", "Invalid login Credentials!");
		return "redirect:/";
	}
}
@RequestMapping("/dashboard")
public String dashboard(Model model, HttpSession session, RedirectAttributes redirect) {
	Long userId = (Long)session.getAttribute("userId");
	if(userId == null) {
		redirect.addFlashAttribute("please", "Please register or login!");
		return "redirect:/";
	}
	model.addAttribute("user", userServ.findUserById(userId));
	return "HomePage.jsp";
}
@RequestMapping("/logout")
public String logout(HttpSession session) {
	session.invalidate();
	return "redirect:/";
	
}
}



	


