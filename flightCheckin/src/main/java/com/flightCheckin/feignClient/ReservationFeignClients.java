package com.flightCheckin.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Enable feignCleint in @SpringBootApplication class.

// This type is to directly access the other application using the url.
//@FeignClient(url = "${com.flightCheckin.flightReservation.url}", value = "reservation-feign-client", path = "/reserve-Control")

// This type is to access the other service throug the eureka service.
@FeignClient(value = "FLIGHT-RESERVATION/SarathFlight", path = "/reserve-Control")
public interface ReservationFeignClients 
{
	@GetMapping("/reservations/{id}")
    public ResponseEntity<?> findReservationFeignInterface(@PathVariable Long id);

}
