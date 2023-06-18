package com.sarath.flightreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sarath.flightreservation.entities.User;
import com.sarath.flightreservation.repos.UserRepo;

@Controller
public class UserController 
{
	@Autowired
	UserRepo userRepo;
	
	@GetMapping("/showRegPg")
	public String showRegistrationPage()
	{
		return "login/registerUser";
	}
	
	//https://howtodoinjava.com/spring-mvc/controller-getmapping-postmapping/
	@PostMapping("/registerUser")
	public String register(@ModelAttribute("user") User user)
	{
		userRepo.save(user);
		return "login/login";
	}
	
}
