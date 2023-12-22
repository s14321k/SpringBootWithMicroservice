package com.sarath.flightreservation.service;

import com.sarath.flightreservation.dto.ReservationRequest;
import com.sarath.flightreservation.entities.Reservation;

public interface ReservationService 
{
	public Reservation bookFlight(ReservationRequest reservationRequest);
}
