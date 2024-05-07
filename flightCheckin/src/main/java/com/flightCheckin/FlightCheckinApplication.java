package com.flightCheckin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
//@EnableEurekaClient	// To get this annotation, add dependency manager in pom.xml.
public class FlightCheckinApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightCheckinApplication.class, args);
	}

}
