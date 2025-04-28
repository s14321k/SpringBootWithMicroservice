package com.springOAuthJWTServer.config.jwtAuth;

import java.time.Instant;
import java.util.Objects;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.springOAuthJWTServer.config.UserInfoConfig;
import com.springOAuthJWTServer.repo.UserInfoRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenUtils {
	
	public String getUserName(Jwt jwtToken) {
		log.info("[JwtTokenUtils:getUserName] :: Started ");
		return jwtToken.getSubject();
	}
	
	/**
	 * Validates if the given JWT token is valid by two criteria:
	 * 1) The token should not be expired.
	 * 2) The username in the token should match the username of the given UserDetails.
	 *
	 * @param jwtToken the JWT token to validate
	 * @param userDetails the UserDetails to validate against
	 * @return true if the token is valid, false otherwise
	 */
	public boolean isTokenValid(Jwt jwtToken, UserDetails userDetails) {
		log.info("[JwtTokenUtils : isTokenValid] :: Started ");
		final String userName = getUserName(jwtToken);
		boolean isTokenExpired = getIfTokenIsExpired(jwtToken);
		boolean isTokenUserSameAsDatabase = userName.equals(userDetails.getUsername());
		return !isTokenExpired  && isTokenUserSameAsDatabase;
	}
	
	private boolean getIfTokenIsExpired(Jwt jwtToken) {
		log.info("[JwtTokenUtils : getIfTokenIsExpired] :: Started ");
		return Objects.requireNonNull(jwtToken.getExpiresAt()).isBefore(Instant.now());
	}
	
	private final UserInfoRepo useruserInfoRepo;
    public UserDetails userDetails(String emailId){
    	log.info("[JwtTokenUtils : userDetails] :: Started ");
        return useruserInfoRepo
                .findByEmailId(emailId)
                .map(UserInfoConfig::new)
                .orElseThrow(()-> new UsernameNotFoundException("UserEmail: "+emailId+" does not exist"));
    }
}
