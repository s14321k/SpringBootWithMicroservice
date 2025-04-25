package com.springOAuthJWTServer.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
public class MessageFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("[MessageFiler] - Inside do filter method");
		log.info("Local Port :"+ request.getLocalPort());
		log.info("Server Name :"+request.getServerName());
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		log.info("Method Name : " + httpServletRequest.getMethod());
		log.info("Request URI : " + httpServletRequest.getRequestURI());
		log.info("Servlet Path : " + httpServletRequest.getServletPath());
		chain.doFilter(request, response);
	}

}
