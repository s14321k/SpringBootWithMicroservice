package com.sarath.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarath.flightreservation.entities.Passenger;

@Repository
public interface PassengerRepo extends JpaRepository<Passenger, Long>
{

}
