package com.sarath.flightreservation.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;


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
