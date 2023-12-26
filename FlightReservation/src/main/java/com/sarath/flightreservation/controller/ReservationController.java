package com.sarath.flightreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sarath.flightreservation.dto.ReservationRequest;
import com.sarath.flightreservation.dto.ReservationUpdateRequest;
import com.sarath.flightreservation.entities.Flight;
import com.sarath.flightreservation.entities.Reservation;
import com.sarath.flightreservation.repos.FlightRepo;
import com.sarath.flightreservation.repos.ReservationRepo;
import com.sarath.flightreservation.service.ReservationService;

@RestController
@RequestMapping("/reserve-Control")
public class ReservationController
{
    @Autowired
    FlightRepo flightRepo;
    
    @Autowired
    ReservationService reservationService;
    
    @Autowired
    ReservationRepo reservationRepo;
    
    @GetMapping("/show-completeReservation")
    public ResponseEntity<?> showCompleteReservation(@RequestParam("flightId") Long id)
    {
        Flight flight = flightRepo.findById(id).orElse(null);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }
    
    @PostMapping("/completeReservation")
    public ResponseEntity<?> completeReservation(@ModelAttribute ReservationRequest request)
    {
    	Reservation reservation = reservationService.bookFlight(request);
    	String reserved = "Succesfully got reserved under id "+ reservation.getId();
    	return new ResponseEntity<>(reserved, HttpStatus.OK);
    }
    
    @GetMapping("/reservations/{id}")
    public ResponseEntity<?> findReservation(@PathVariable Long id)
    {
    	Reservation res = reservationRepo.findById(id).orElse(null);
    	return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
    @PostMapping("/updateReservation")
    public ResponseEntity<?> updateReservation(@RequestBody ReservationUpdateRequest res)
    {
    	Reservation reservation = reservationRepo.findById(res.getId()).orElse(null);
    	reservation.setNumberOfBags(res.getNumberOfBags());
    	reservation.setCheckedIn(res.getCheckIn());
    	return new ResponseEntity<>(reservationRepo.save(reservation), HttpStatus.OK);
    }
}
