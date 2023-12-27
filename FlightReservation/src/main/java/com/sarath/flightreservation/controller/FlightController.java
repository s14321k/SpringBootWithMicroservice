package com.sarath.flightreservation.controller;

import com.sarath.flightreservation.entities.Flight;
import com.sarath.flightreservation.repos.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/flightControl")
public class FlightController {

    @Autowired
    FlightRepo flightRepo;

    @GetMapping("/findFlight")
    public ResponseEntity<?> findFlight(@RequestParam(value = "from") String departure,
                                        @RequestParam(value = "to") String arrival,
                                        @RequestParam(value = "departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") Date departureDate) {

        List<Flight> flightList = flightRepo.findFlights(departure, arrival, departureDate);
        return new ResponseEntity<>(flightList, HttpStatus.OK);
    }

    @PostMapping("/add-flights")
    public ResponseEntity<?> saveFlight(@RequestBody List<Flight> flight)
    {
        try
        {
//            flight.forEach(fligts -> flightRepo.save(fligts));
            List<Flight> savaedFlights = flightRepo.saveAll(flight);
            return new ResponseEntity<>(savaedFlights, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
