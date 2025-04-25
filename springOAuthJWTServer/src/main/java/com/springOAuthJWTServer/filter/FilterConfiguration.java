package com.springOAuthJWTServer.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfiguration {

	// Registering a filter
//	@Bean
	public FilterRegistrationBean<MessageFilter> registrationBean() {
		FilterRegistrationBean<MessageFilter> registrationBean = new FilterRegistrationBean<MessageFilter>();
		registrationBean.setFilter(new MessageFilter());
		registrationBean.addUrlPatterns("/sign-in");
		return registrationBean;
	}
}
