package com.sarath.flightreservation.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Reservation
{
    //private Long id;
    private Boolean checkedIn;
    private int numberOfBags;
    @OneToOne
    private Passenger passenger;
    @OneToOne
    private Flight flight;

    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/

    public Boolean getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(Boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public int getNumberOfBags() {
        return numberOfBags;
    }

    public void setNumberOfBags(int numberOfBags) {
        this.numberOfBags = numberOfBags;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
