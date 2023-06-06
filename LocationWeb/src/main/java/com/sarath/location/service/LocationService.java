package com.sarath.location.service;

import com.sarath.location.entities.Location;

import java.util.List;

public interface LocationService
{
    Location saveLocation(Location location);
    Location updateLocation(Location location);
    void deleteLocation(Location location);
    Location getLocationByID(int id);
    List<Location> getAllLocation();
}
