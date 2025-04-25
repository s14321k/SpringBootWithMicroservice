package com.springOAuthJWTServer.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springOAuthJWTServer.config.UserInfoConfig;
import com.springOAuthJWTServer.config.jwtConfig.JwtTokenGenerator;
import com.springOAuthJWTServer.dto.AuthResponseDto;
import com.springOAuthJWTServer.repo.UserInfoRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

	private final UserInfoRepo userInfoRepo;
	
	private final JwtTokenGenerator jwtTokenGenerator;

	public AuthResponseDto getJwtTokensAfterAuthentication(Authentication authentication) {
		try {
			var userInfoEntity = userInfoRepo.findByEmailId(authentication.getName())
					.orElseThrow(() -> {
						log.error("[AuthService:userSignInAuth] User :{} not found", authentication.getName());
						return new ResponseStatusException(HttpStatus.NOT_FOUND, "USER NOT FOUND");
					});
			
			String accessToken = jwtTokenGenerator.generateAccessToken(authentication);
			
			log.info("[Authservice:userSignInAuth]Access token for user :{}, has been generated", userInfoEntity.getUserName());
			return AuthResponseDto.builder()
					.accessToken(accessToken)
					.accessTokenExpiry(15 * 60)
					.userName(userInfoEntity.getUserName())
					.tokenType(TokenType.BEARER)
					.build();
		}
		catch (Exception e) {
			log.error("[AuthService:userSignInAuth]Exception while authenticating the user due to :" +e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Please try again");
		}
	}
}
