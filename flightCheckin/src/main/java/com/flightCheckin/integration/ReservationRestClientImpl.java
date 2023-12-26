package com.flightCheckin.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.flightCheckin.integration.dto.Reservation;
import com.flightCheckin.integration.dto.ReservationUpdateRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReservationRestClientImpl implements ReservationRestClient
{
	
	// Rest client introduced in 3.2
	// https://howtodoinjava.com/spring/spring-restclient/
	
	private RestClient restClient;
	
	public ReservationRestClientImpl()
	{
		restClient = RestClient.builder()
							   .baseUrl("http://localhost:8082/SarathFlight")
							   .build();
	}
	
	@Override
	public Reservation findReservation(Long id)
	{
		return restClient.get().uri("/reserve-Control/reservations/"+id).retrieve().body(Reservation.class);
	}

	@Override
	public Reservation updateReservation(ReservationUpdateRequest resUpdate)
	{
		return restClient.post().uri("/reserve-Control/updateReservation").contentType(MediaType.APPLICATION_JSON).body(resUpdate).retrieve().body(Reservation.class);
	}

}
