package com.springBATCH.propConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component

// We can use one or more property sources
@PropertySource("application-local.yml")
@PropertySource("application-dev.yml")

// Profile value and application.yml profile value should be same.
// This bean will be created only in a certain profile.
// If we use ! then appart from dev every thing will create the bean. eg: @Profile("!dev")
@Profile("dev")
// @Profile("local")
public class PropConfig 
{
	@Value("${com.message}")
	public String message;
	
	@Value("com.local.message")
	public String localMessage;
	
	@Value("${spring.profiles.active}")
	public String activeProfile;
	
	@PostConstruct
	public void printMessage()
	{
		System.out.println("-----------------------------------------------------------------------------  Properties file config ->>>>>>>>>>>>>> "+message+" ------------------------------------------------");
		System.out.println("-----------------------------------------------------------------------------  Properties file local ->>>>>>>>>>>>>>> "+localMessage+" -------------------------------------------");
		System.out.println("-----------------------------------------------------------------------------  Properties file ActiveProfile ->>>>>>> "+activeProfile+" ------------------------------------------");
	}

}
