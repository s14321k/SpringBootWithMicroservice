package com.spring.security.config;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests((requests) -> requests.requestMatchers("/myAccount",
																			"/myBalance", 
																			"/myCards", 
																			"/myLoans")
														.authenticated()
														.requestMatchers("/notices", 
																		"/contacts",
																		"/h2/**")
														.permitAll());
		
		// This will permit all the requests from front end
//		http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
		
		// This will deny all the requests from front end
//		http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
		
		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		return http.build();
	}
	
	
	/* Deprecated.  Using this method is not considered safe for production, 
	 * but isacceptable for demos and getting started. 
	 * For production purposes, ensure thepassword is encoded externally. 
	 * See the method Javadoc for additional details.
	 * There are no plans to remove this support. 
	 * It is deprecated to indicate that thisis considered insecure for production purposes.*/
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//		UserDetails admin = User.withDefaultPasswordEncoder()
//								.username("admin")
//								.password("1234")
//								.authorities("admin")
//								.build();
//		// We can create multiple userdetails and pass multiple parameters.
//		return new InMemoryUserDetailsManager(admin);
//	}
	
	@Bean
	DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2)
			.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
			.build();
	}
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		return null;
	}

}
