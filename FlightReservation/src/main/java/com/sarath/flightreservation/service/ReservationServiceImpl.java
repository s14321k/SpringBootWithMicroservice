package com.sarath.flightreservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarath.flightreservation.dto.ReservationRequest;
import com.sarath.flightreservation.entities.Flight;
import com.sarath.flightreservation.entities.Passenger;
import com.sarath.flightreservation.entities.Reservation;
import com.sarath.flightreservation.repos.FlightRepo;
import com.sarath.flightreservation.repos.PassengerRepo;
import com.sarath.flightreservation.repos.ReservationRepo;
import com.sarath.flightreservation.util.EmailUtil;
import com.sarath.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService
{
	@Autowired
	FlightRepo flightRepo;
	
	@Autowired
	PassengerRepo passengerRepo;
	
	@Autowired
	ReservationRepo reservationRepo;
	
	@Autowired
	PDFGenerator pdfGen;
	
	@Autowired
	EmailUtil email;

	@Override
	public Reservation bookFlight(ReservationRequest reservationRequest) 
	{
//		reservationRequest.getCardNumber()
		Long flightID = reservationRequest.getFlightId();
		Flight flight = flightRepo.getReferenceById(flightID);
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(reservationRequest.getPassangerFirstName());
		passenger.setLastName(reservationRequest.getPassangerLastName());
		passenger.setPhone(reservationRequest.getPassangerPhone());
		passenger.setEmail(reservationRequest.getPassangerEmail());
		Passenger savedPassenger = passengerRepo.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		Reservation savedReservation = reservationRepo.save(reservation);
		
		String path = pdfGen.generatateItinary(savedReservation, savedReservation.getId());
		
		email.sendMail("silvershinesarath@gmail.com", path);
		
		return savedReservation;
	}
	
}
