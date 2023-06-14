package com.sarath.location.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarath.location.entities.Location;
import com.sarath.location.repository.LocationRepo;

@RestController
@RequestMapping("/restLocations")
public class LocationRESTController 
{
	@Autowired
	LocationRepo locationRepo;
	
	@GetMapping
	public List<Location> getLocations()
	{
		return locationRepo.findAll();
	}
	
	@PostMapping
	public Location createLocation(@RequestBody Location location) //@RequestBody will deserialize the values  and set in location
	{
		return locationRepo.save(location);
	}
	
	@PutMapping
	public Location updateLocation(@RequestBody Location location)
	{
		return locationRepo.save(location);
	}
	
	@DeleteMapping("/{id}")
	public void deleteLocation(@PathVariable("id") int id)
	{
		locationRepo.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Location getLocation(@PathVariable("id") int id)
	{
		return locationRepo.findById(id).orElse(null);
	}
}
