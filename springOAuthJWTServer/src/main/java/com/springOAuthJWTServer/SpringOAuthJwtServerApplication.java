package com.springOAuthJWTServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.springOAuthJWTServer.config.RSAKeyRecord;

@EnableConfigurationProperties(RSAKeyRecord.class)
@SpringBootApplication
public class SpringOAuthJwtServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringOAuthJwtServerApplication.class, args);
	}

}
