package com.springBATCH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBatchApplication {

	public static void main(String[] args) 
	{	
		// Bay way to set profiling
//		SpringApplication application = new SpringApplication(SpringBatchApplication.class);
//		application.setAdditionalProfiles("prod");
//		application.run(args);
		SpringApplication.run(SpringBatchApplication.class, args);
	}

}
