package com.sarath.location.controller;

import com.sarath.location.entities.Location;
import com.sarath.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // -> This returns the CreateLocation.jsp page in return
//@RestController -> This returns only the name CreateLocation in return
@RequestMapping("LocCon")
public class LocationController
{
	@Autowired
    LocationService locationService;
	
    @GetMapping("/ShowCreateJsp")
    public String showCreate()
    {
        return "CreateLocation";
    }

    @PostMapping("/saveNewLocation")
    public String saveNewLocation(@ModelAttribute("location") Location location, ModelMap map)
    {
        Location saved = locationService.saveLocation(location);
        String msg = "Location saved with id : "+saved.getId();
        map.addAttribute("msg", msg);   // in CreateLocation.jsp ${msg}
        return "CreateLocation";
    }

    @GetMapping("/listAllLocations")
    public String displayLocations(ModelMap map)
    {
    	List<Location> listLocation = locationService.getAllLocation();
        map.addAttribute("listLocation", listLocation);
    	return "displayAllLocations";
    }
}
