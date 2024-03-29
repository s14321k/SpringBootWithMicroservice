package com.flightCheckin.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Enable feignCleint in @SpringBootApplication class.
@FeignClient(url = "${com.flightCheckin.flightReservation.url}", value = "reservation-feign-client", path = "/reserve-Control")
public interface ReservationFeignClients 
{
	@GetMapping("/reservations/{id}")
    public ResponseEntity<?> findReservation(@PathVariable Long id);

}
