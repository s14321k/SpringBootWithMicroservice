package com.sarath.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarath.flightreservation.entities.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FlightRepo extends JpaRepository<Flight, Long> 
{
    @Query("from Flight where departure_city=:departureCity and arrival_city=:arrivalCity and date_of_departure=:dateOfDeparture")
    List<Flight> findFlights(@Param("departureCity") String departure, @Param("arrivalCity") String arrival, @Param("dateOfDeparture") Date depDate);
}
