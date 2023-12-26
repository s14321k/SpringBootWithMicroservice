package com.flightCheckin.integration.dto;

import lombok.Data;

@Data
public class ReservationRequest 
{
	private Long flightId;
	private String passangerFirstName;
	private String passangerLastName;
	private String passangerEmail;
	private String passangerPhone;
	private String nameOnTheCard;
	private String cardNumber;
	private String expireDate;
	private String securityCode;
}
