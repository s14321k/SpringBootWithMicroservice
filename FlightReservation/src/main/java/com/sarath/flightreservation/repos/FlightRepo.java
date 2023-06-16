package com.sarath.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarath.flightreservation.entities.Flight;

public interface FlightRepo extends JpaRepository<Flight, Long> 
{

}
