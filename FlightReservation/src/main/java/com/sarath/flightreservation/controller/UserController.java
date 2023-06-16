package com.sarath.flightreservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController 
{
	@RequestMapping("/showRegPg")
	public String showRegistrationPage()
	{
		return "login/registerUser";
	}
}
