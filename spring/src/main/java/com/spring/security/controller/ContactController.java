package com.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class ContactController {
	
	@GetMapping("/contacts")
	public String saveContactEnquiryDetails() {
		return "Inquiry Details are saved to the DB";
	}
}
