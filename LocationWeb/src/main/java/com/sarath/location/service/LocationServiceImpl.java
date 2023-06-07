package com.sarath.location.service;

import com.sarath.location.entities.Location;
import com.sarath.location.repository.LocationRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService
{
	@Autowired
    LocationRepo locationRepo;

    @Override
    public Location saveLocation(Location location)
    {
        return locationRepo.save(location);
    }

    @Override
    public Location updateLocation(Location location) {
        return locationRepo.save(location);
    }

    @Override
    public void deleteLocation(Location location)
    {
        locationRepo.delete(location);
    }

    @Override
    public Location getLocationByID(int id) {
        return locationRepo.findById(id).orElse(null);
    }

    @Override
    public List<Location> getAllLocation()
    {
        //To get converted into list, we can extend JpaRepository in LocRepo
        return locationRepo.findAll();
    }
}
