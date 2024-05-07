package com.sarath.flightreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //(scanBasePackages = {"com.sarath.flightreservation"})
//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class} )
//@EnableEurekaClient
public class FlightReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightReservationApplication.class, args);
    }
}
