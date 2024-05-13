package com.flightCheckin.integration.dto;

import lombok.*;

@Data
public class Reservation
{
	private Long id;
    private Boolean checkedIn;
    private int numberOfBags;
    private Passenger passenger;
    private Flight flight;
}
