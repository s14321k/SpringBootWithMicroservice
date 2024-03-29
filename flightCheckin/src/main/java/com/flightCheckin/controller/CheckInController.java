package com.flightCheckin.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightCheckin.integration.ReservationRestClient;
import com.flightCheckin.integration.dto.Reservation;
import com.flightCheckin.integration.dto.ReservationUpdateRequest;

@RestController
@RequestMapping("/flightCheckIn")
public class CheckInController
{
	
	@Autowired
	public ReservationRestClient reservationRestClient;
	
	// Create a page in Angular
	public String showStartCheckIn()
	{
		return "showStartCheckinPage";
	}
	
	@GetMapping("/startCheckin")
	public ResponseEntity<?> startCheckIn(@RequestParam("reservationId") Long id)
	{
		Reservation reserv = new Reservation();
		Object res = reservationRestClient.findReservation(id);
		BeanUtils.copyProperties(res, reserv);
		return new ResponseEntity<>(res, HttpStatus.OK); // forword to Display Reservation details page
	}
	
	@PutMapping("/completeCheckIn")
	public ResponseEntity<?> completeCheckIn(@RequestBody ReservationUpdateRequest resrvationUpdateReq)
	{
		resrvationUpdateReq.setCheckIn(true);
		return new ResponseEntity<>(reservationRestClient.updateReservation(resrvationUpdateReq), HttpStatus.OK);
	}
}
