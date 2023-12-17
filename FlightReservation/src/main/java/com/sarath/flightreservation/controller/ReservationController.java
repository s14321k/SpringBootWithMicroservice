package com.sarath.flightreservation.controller;

import com.sarath.flightreservation.entities.Flight;
import com.sarath.flightreservation.repos.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserve-Control")
public class ReservationController
{
    @Autowired
    FlightRepo flightRepo;
    @GetMapping("/show-completeReservation")
    public ResponseEntity<?> showCompleteReservation(@RequestParam("flightId") Long id)
    {
        Flight flight = flightRepo.findById(id).orElse(null);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }
}
