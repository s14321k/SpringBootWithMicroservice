package com.flightCheckin.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.flightCheckin.feignClient.ReservationFeignClients;
import com.flightCheckin.integration.dto.Reservation;
import com.flightCheckin.integration.dto.ReservationUpdateRequest;

@Service
public class ReservationRestClientImpl implements ReservationRestClient
{
	
	// Rest client introduced in 3.2
	// https://howtodoinjava.com/spring/spring-restclient/
	@Value("${com.flightReservation.url}")
	private String BASE_URL;
	
	private RestClient restClient;
	
	private ReservationFeignClients reservationFeignClients;
	
	public ReservationRestClientImpl()
	{
		restClient = RestClient.builder()
							   .baseUrl(BASE_URL)
							   .build();
	}
	
	@Override
	public ResponseEntity<?> findReservation(Long id)
	{
		ResponseEntity<Reservation> resResp = (ResponseEntity<Reservation>) reservationFeignClients.findReservation(id).getBody();
		
		
//		return restClient.get().uri("/reserve-Control/reservations/"+id).retrieve().body(Reservation.class);
		return resResp;
	}

	@Override
	public ResponseEntity<?> updateReservation(ReservationUpdateRequest resUpdate)
	{
		// https://github.com/spring-projects/spring-framework/blob/699f93fed71f7bfd73d94188dce6b849c92927cc/framework-docs/modules/ROOT/pages/integration/rest-clients.adoc
		ResponseEntity<Reservation> res = restClient.post()
				.uri("/reserve-Control/updateReservation")
				.contentType(MediaType.APPLICATION_JSON)
				.body(resUpdate)
				.retrieve()
				.toEntity(Reservation.class);
		return res;
	}

}
