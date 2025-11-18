package com.flightCheckin.integration;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.flightCheckin.feignClient.ReservationFeignClients;
import com.flightCheckin.integration.dto.Reservation;
import com.flightCheckin.integration.dto.ReservationUpdateRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ReservationRestClientImpl implements ReservationRestClient
{
	
	Logger log = LoggerFactory.getLogger(ReservationRestClientImpl.class);
	long count = 0;
	
	@Autowired
	private ReservationFeignClients reservationFeignClients;
	
	private RestClient restClient;
	
	@Value("${com.flightCheckin.flightReservation.url}")
	private String BASE_URL;
	
	
	/*
	 * RestClient introduced in 3.2
	 * https://howtodoinjava.com/spring/spring-restclient/
	 */		
	public ReservationRestClientImpl()
	{
		
		restClient = RestClient.builder()
							   .baseUrl(BASE_URL)
							   .build();
	}
	
	// "flightReservationService" should be same as in the resilience properties
	@Override
	@Retry(name = "flightReservationService")
	@CircuitBreaker(name = "flightReservationService", fallbackMethod = "fallbackGetReservationById")
	@RateLimiter(name = "flightReservationService")
	@TimeLimiter(name = "flightReservationService")
	@Bulkhead(name = "flightReservationService", type = Bulkhead.Type.SEMAPHORE)
	public Object findReservation(Long id)
	{
		//-----Using FeignClient-------------//
		Object resp = reservationFeignClients.findReservationFeignInterface(id).getBody();
		log.info("count"+count);
		count++;
		return resp;
		
		//------Using RestClient------------//
		//return restClient.get().uri("/reserve-Control/reservations/"+id).retrieve().body(Object.class);
	}
	
	public Object fallbackGetReservationById(Long id, Throwable e) {
		log.error("Error = "+ e);
		Reservation res = new Reservation();
		res.setId(id);
		return res;
	}

	@Override
	public ResponseEntity<?> updateReservation(ReservationUpdateRequest resUpdate)
	{
		// https://github.com/spring-projects/spring-framework/blob/699f93fed71f7bfd73d94188dce6b849c92927cc/framework-docs/modules/ROOT/pages/integration/rest-clients.adoc
		ResponseEntity<Reservation> res = restClient.post()
				.uri(BASE_URL+"/reserve-Control/updateReservation")
				.contentType(MediaType.APPLICATION_JSON)
				.body(resUpdate)
				.retrieve()
				.toEntity(Reservation.class);
		return res;
	}

}
