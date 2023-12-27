package com.sarath.flightreservation.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarath.flightreservation.entities.User;
import com.sarath.flightreservation.repos.UserRepo;
import com.sarath.flightreservation.service.SecurityService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 6000)
@RestController
@RequestMapping("/userControl")
public class UserController 
{
	private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	private SecurityService securityService;

//	private static final String VALID_EMAIL ="";

	@GetMapping("/showRegPg")
	public String showRegistrationPage()
	{
		return "registerUserPage";
	}
	
	//https://howtodoinjava.com/spring-mvc/controller-getmapping-postmapping/
	@PostMapping("/registerUser")
	@Validated
	public String register(@Valid @RequestBody User user)
	{
		System.out.println("User.get " + user.getPassword());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User saved = userRepo.save(user);
		return saved.getEmail();
	}

	@PostMapping("/loginAuthenticate")
	public Boolean authenticateLogin(@RequestBody Map<String, Object> body)
	{
		LOGGER.error("ERROR");
		LOGGER.warn("WARN");
		LOGGER.info("INFO");
		LOGGER.debug("DEBUG");
		LOGGER.trace("TRACE");
		
		String email = (String) body.get("email");
		String password = (String) body.get("password");
		System.out.print("email : "+ email + "\n password : " + password);
		boolean exists = userRepo.existsByEmailAndPassword(email, password);
		
		boolean loginRes = securityService.login(email, password);
		
		if(exists != loginRes)
			LOGGER.error("Exists or not ?> Debug the Code o/o/o/o/o/o/o/o/o/o/o/o/o/o/o/o/o/o/o/o/o/o Debug the Code");
		
		return loginRes;
	}
}
