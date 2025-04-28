package com.sarath.flightreservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sarath.flightreservation.entities.User;
import com.sarath.flightreservation.repos.UserRepo;

public class UserDetailsServiceImpl implements UserDetailsService 
{
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		User user = userRepo.findByEmail(username);
		if(user == null) throw new UsernameNotFoundException("No User found by this name "+username);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRoles());
	}

}
