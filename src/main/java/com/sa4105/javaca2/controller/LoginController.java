package com.sa4105.javaca2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sa4105.javaca2.model.User;
import com.sa4105.javaca2.service.UserService;
import com.sa4105.javaca2.service.UserServiceImpl;

@Controller
public class LoginController {
	
	@Autowired
	UserService uservice;
	
	public LoginController(UserServiceImpl userviceImpl) {
		super();
		this.uservice = userviceImpl;
	}
	
	@GetMapping("")
	public String login(@ModelAttribute ("user") User user, HttpSession session) {
		user = new User();
		System.out.println("This is the GetMapping at HomePage");
		return "index";
	}
	
	@PostMapping("")
	public String login2(@ModelAttribute ("user") User user, HttpSession session) {
		System.out.println("This is the PostMapping at HomePage");
		//System.out.println(user.getUsername());
		user = uservice.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (user != null) {
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
			session.setAttribute("username", user.getUsername());
			session.setAttribute("role", user.getRole().getRoleName());
			session.setAttribute("roleid", user.getRole().getId());
			session.setAttribute("user", user);
			return "redirect:/user/" + user.getUsername();
		}
			

		else
			return "redirect:/";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(@ModelAttribute ("user") User user, HttpSession session) {
		user = uservice.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (session.getAttribute("username") != null) {
			session.removeAttribute("username");
			session.removeAttribute("role");
			System.out.println("User has logged out");
			return "redirect:/";
		}
		else
			return "redirect:/";
	}

}
