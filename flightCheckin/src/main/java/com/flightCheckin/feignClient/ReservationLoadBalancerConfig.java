package com.flightCheckin.feignClient;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

import feign.Feign;

@LoadBalancerClient(value = "FLIGHT-RESERVATION")
public class ReservationLoadBalancerConfig {
	
	@Bean
	@LoadBalanced
	public Feign.Builder feiBuilder() {
		return Feign.builder();
	}

}
