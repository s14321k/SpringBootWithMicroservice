package com.sarath.flightreservation.dto;

import lombok.Data;

@Data
public class ReservationUpdateRequest 
{
	private Long id;
	private Boolean checkIn;
	private int numberOfBags;
}
