package com.sarath.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarath.flightreservation.entities.Reservation;

public interface ReservationRepo extends JpaRepository<Reservation, Long>
{

}
