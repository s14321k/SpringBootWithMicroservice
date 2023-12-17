package com.sarath.flightreservation.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation extends AbstractEntity
{
    private Boolean checkedIn;
    private int numberOfBags;
    @OneToOne
    private Passenger passenger;
    @OneToOne
    private Flight flight;
}
