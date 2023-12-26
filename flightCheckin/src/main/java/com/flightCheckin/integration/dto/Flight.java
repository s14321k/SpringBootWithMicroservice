package com.flightCheckin.integration.dto;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight
{
	private Long id;
    private String flight_number;
    private String operating_airlines;
    private String departure_city;
    private String arrival_city;
    @JsonFormat(pattern = "MM-dd-yyyy")
    private Date date_of_departure;
    private Timestamp estimate_departure_time;
}
