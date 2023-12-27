package com.sarath.flightreservation.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig	//WebSecurityConfigurerAdaptor is depricated in spring 3.0 
{
	//https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter/
	//https://stackoverflow.com/a/76856683
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer()
	{
		return (web) -> web.ignoring().requestMatchers("/registerUser", "/", "loginAuthenticate", "/add-flights", "/**");
	}
	
	/*
	 * public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws
	 * Exception { httpSecurity .authorizeHttpRequests((authz) -> authz.anyRequest()
	 * .authenticated() ) .httpBasic(withDefaults()); }
	 */
}
