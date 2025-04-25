package com.sarath.flightreservation.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sarath.flightreservation.dto.ReservationRequest;
import com.sarath.flightreservation.dto.ReservationUpdateRequest;
import com.sarath.flightreservation.entities.Flight;
import com.sarath.flightreservation.entities.Reservation;
import com.sarath.flightreservation.repos.FlightRepo;
import com.sarath.flightreservation.repos.ReservationRepo;
import com.sarath.flightreservation.service.ReservationService;

@ExtendWith(MockitoExtension.class)
public class ReservationControllerTest {

	@Mock
    FlightRepo flightRepo;
    
	@Mock
    ReservationService reservationService;
    
	@Mock
    ReservationRepo reservationRepo;
	
	@InjectMocks
	ReservationController reservationController;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	@Test
    public void testShowCompleteReservation() {
        Long flightId = 1L;
        Flight flight = new Flight(); // Create a flight object here
        
        when(flightRepo.findById(flightId)).thenReturn(java.util.Optional.of(flight));
        
        ResponseEntity<?> response = reservationController.showCompleteReservation(flightId);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(flight, response.getBody());
    }
	
	@Test
    public void testCompleteReservation() {
        ReservationRequest request = new ReservationRequest(); // Create a request object here
        Reservation reservation = new Reservation(); // Create a reservation object here
        
        when(reservationService.bookFlight(request)).thenReturn(reservation);
        
        ResponseEntity<?> response = reservationController.completeReservation(request);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Succesfully got reserved under id " + reservation.getId(), response.getBody());
    }
    
    @Test
    public void testFindReservation() {
        Long reservationId = 1L;
        Reservation reservation = new Reservation(); // Create a reservation object here
        
        when(reservationRepo.findById(reservationId)).thenReturn(java.util.Optional.of(reservation));
        
        ResponseEntity<?> response = reservationController.findReservation(reservationId);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservation, response.getBody());
    }
    
    @Test
    public void testUpdateReservation() {
        ReservationUpdateRequest request = new ReservationUpdateRequest(); // Create a request object here
        request.setId(1L); // Set id
        request.setNumberOfBags(2); // Set number of bags
        request.setCheckIn(true); // Set check-in
        
        Reservation reservation = new Reservation(); // Create a reservation object here
        
        when(reservationRepo.findById(request.getId())).thenReturn(java.util.Optional.of(reservation));
        when(reservationRepo.save(reservation)).thenReturn(reservation);
        
        ResponseEntity<?> response = reservationController.updateReservation(request);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservation, response.getBody());
        assertEquals(request.getNumberOfBags(), reservation.getNumberOfBags());
        assertEquals(request.getCheckIn(), reservation.getCheckedIn());
    }
}
