package com.springOAuthJWTServer.config.jwtConfig;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nimbusds.jose.proc.SecurityContext;
import com.springOAuthJWTServer.config.RSAKeyRecord;
import com.springOAuthJWTServer.dto.TokenType;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtAccessTokenFilter extends OncePerRequestFilter{
	
	private final RSAKeyRecord rsaKeyRecord;
	private final JwtTokenUtils jwtTokenUtils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			log.info("[JwtAccessTokenFilter:doFilterInternal] :: Started ");
			
			log.info("[JwtAccessTokenFilter:doFilterInternal]Filtering the Http Request:{}",request.getRequestURI());
			
			final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
			
			JwtDecoder jwtDecoder = NimbusJwtDecoder.withPublicKey(rsaKeyRecord.rsaPublicKey()).build();
			
			if(!authHeader.startsWith(TokenType.Bearer.name())) {
				filterChain.doFilter(request, response);
				return;
			}
			
			final String token = authHeader.substring(7);
			final Jwt jwtToken = jwtDecoder.decode(token);
			
			final String userName = jwtTokenUtils.getUserName(jwtToken);
			
			if(!userName.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				UserDetails userDetails = jwtTokenUtils.userDetails(userName);
				if(jwtTokenUtils.isTokenValid(jwtToken,userDetails)) {
					SecurityContext securityContext = (SecurityContext) SecurityContextHolder.createEmptyContext();
				}
			}
		} 
		catch (JwtValidationException jwtValidationException) {
			
		}
		
	}

}
