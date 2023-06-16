package com.sarath.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarath.flightreservation.entities.Passenger;

public interface PassengerRepo extends JpaRepository<Passenger, Long>
{

}
