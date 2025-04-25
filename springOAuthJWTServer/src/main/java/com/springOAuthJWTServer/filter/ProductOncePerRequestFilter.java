package com.springOAuthJWTServer.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
public class ProductOncePerRequestFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("[ProductFilter] - Inside do filter method");
		log.info("Local Port :"+ request.getLocalPort());
		log.info("Server Name :"+request.getServerName());
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		log.info("Product Name : " + httpServletRequest.getMethod());
		log.info("Request URI : " + httpServletRequest.getRequestURI());
		log.info("Servlet Path : " + httpServletRequest.getServletPath());
		chain.doFilter(request, response);
	}
}
