package com.springOAuthJWTServer.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springOAuthJWTServer.repo.UserInfoRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoManagerConfig implements UserDetailsService{
	
	private final UserInfoRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		log.info("[UserInfoManagerConfig.loadUserByUsername()]");
		return userRepo
				.findByEmailId(emailId)
				.map(UserInfoConfig::new)
				.orElseThrow(() -> new UsernameNotFoundException("UserEmail: "+emailId+" does not exist"));
	}

}
