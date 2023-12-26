package com.flightCheckin.integration.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation
{
	private Long id;
    private Boolean checkedIn;
    private int numberOfBags;
    private Passenger passenger;
    private Flight flight;
}
