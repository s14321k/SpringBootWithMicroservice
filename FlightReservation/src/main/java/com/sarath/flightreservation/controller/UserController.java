package com.sarath.flightreservation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.sarath.flightreservation.entities.User;
import com.sarath.flightreservation.repos.UserRepo;

import jakarta.validation.Valid;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 6000)
@RestController
@RequestMapping("/userControl")
public class UserController 
{
	@Autowired
	UserRepo userRepo;

	private static final String VALID_EMAIL ="";

	@GetMapping("/showRegPg")
	public String showRegistrationPage()
	{
		return "registerUserPage";
	}
	
	//https://howtodoinjava.com/spring-mvc/controller-getmapping-postmapping/
	@PostMapping("/registerUser")
	public String register(@RequestBody User user)
	{
		System.out.println("User.get " + user.getPassword());
		User saved = userRepo.save(user);
		return saved.getEmail();
	}

	@PostMapping("/loginAuthenticate")
	public Boolean authenticateLogin(@RequestBody Map<String, Object> body)
	{
		String email = (String) body.get("email");
		String password = (String) body.get("password");
		System.out.print("email : "+ email + "\n password : " + password);
		boolean exists = userRepo.existsByEmailAndPassword(email, password);
		System.out.println("Exists or not ?> "+exists);
		return exists;
	}
}
