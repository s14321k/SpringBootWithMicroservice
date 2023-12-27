package com.flightCheckin.integration;

import org.springframework.http.ResponseEntity;

import com.flightCheckin.integration.dto.Reservation;
import com.flightCheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient
{
	public ResponseEntity<?> findReservation(Long id);
	
	public ResponseEntity<?> updateReservation(ReservationUpdateRequest resUpdate);
}
