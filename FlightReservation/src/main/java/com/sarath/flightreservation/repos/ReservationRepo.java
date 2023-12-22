package com.sarath.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarath.flightreservation.entities.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long>
{

}
