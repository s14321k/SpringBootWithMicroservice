package com.sarath.flightreservation.entities;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Flight extends AbstractEntity
{
    private String flight_number;
    private String operating_airlines;
    private String departure_city;
    private String arrival_city;
    @JsonFormat(pattern = "MM-dd-yyyy")
    private Date date_of_departure;
    private Timestamp estimate_departure_time;
}
