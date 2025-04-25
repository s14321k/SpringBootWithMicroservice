package com.springOAuthJWTServer.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DashboardController {
	
	// hasAnyRole is for Roles
	// hasAuthority is for permissions
//	@PreAuthorize("hasAnyRole('ROLE_MANGER', 'ROLE_ADMIN', 'ROLE_USER')")	//This not works in jwt token
	@PreAuthorize("hasAuthority('SCOPE_READ')")	// This works with jwt token
	@GetMapping("/welcome-message")
	public ResponseEntity<String> getFirstWelcomeMessage(Authentication authentication) {
		return ResponseEntity.ok("Welcome to the JWT Tutorial:" + authentication.getName()+" with scope: "+authentication.getAuthorities());
	}
	
//	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@PreAuthorize("hasAuthority('SCOPE_READ')")
	@GetMapping("/manager-message")
	public ResponseEntity<String> getManagerData(Principal principal) {
		return ResponseEntity.ok("Manager::"+principal.getName());
	}
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PreAuthorize("hasAuthority('SCOPE_WRITE')")
	@GetMapping("/admin-message")
	public ResponseEntity<String> getAdminData(@RequestParam("message") String message, Principal principal) {
		return ResponseEntity.ok("Admin::"+principal.getName()+" has this message:"+message);
	}
}
