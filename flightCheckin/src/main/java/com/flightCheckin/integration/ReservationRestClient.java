package com.flightCheckin.integration;

import com.flightCheckin.integration.dto.Reservation;
import com.flightCheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient
{
	public Reservation findReservation(Long id);
	
	public Reservation updateReservation(ReservationUpdateRequest resUpdate);
}
