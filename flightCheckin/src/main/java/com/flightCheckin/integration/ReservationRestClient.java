package com.flightCheckin.integration;

import org.springframework.http.ResponseEntity;

import com.flightCheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient
{
	// Using feign client
	public Object findReservation(Long id);
	
	// Using rest client
	public ResponseEntity<?> updateReservation(ReservationUpdateRequest resUpdate);
}
