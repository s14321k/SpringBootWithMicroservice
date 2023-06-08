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
    public String displayLocations(ModelMap resModMap)
    {
    	List<Location> listLocation = locationService.getAllLocation();
        resModMap.addAttribute("listLocation", listLocation);
    	return "displayAllLocations";
    }

    @DeleteMapping("/deleteLocation/{id}")
    public String deleteLocation(@PathVariable int id, ModelMap resModMap)
    {
        //Location location = locationService.getLocationByID(id);
        Location location = new Location();
        location.setId(id);
        locationService.deleteLocation(location);
        List<Location> allLocations = locationService.getAllLocation();
        resModMap.addAttribute("allLocations", allLocations);
        return "displayAllLocations";
    }
	/*
	 * @RequestMapping("/deleteLocation") public String
	 * deleteLocation(@RequestParam("id") int id, ModelMap resModMap) { //Location
	 * location = locationService.getLocationByID(id); Location location = new
	 * Location(); location.setId(id); locationService.deleteLocation(location);
	 * List<Location> listLocation = locationService.getAllLocation();
	 * resModMap.addAttribute("listLocation", listLocation); return
	 * "displayAllLocations";
	 */
    
    
    @GetMapping("/updateLocationPage/{id}")
    public String updateLocationPage(@PathVariable int id, ModelMap resModMap)
    {
    	Location editLocation = locationService.getLocationByID(id);
    	resModMap.addAttribute("editLocation", editLocation);
    	return "editLocation";
    }
    
	
	  @PutMapping("/updateLocationValues") 
	  public String updateLocationValues(@ModelAttribute("location") Location location, ModelMap resModMap) 
	  { 
		  locationService.updateLocation(location); 
		  List<Location> allLocations = locationService.getAllLocation();
		  resModMap.addAttribute("allLocations", allLocations); return
		  "displayAllLocations"; 
	  }
	 
    
	/*
	 * @RequestMapping("/updateLocationValues") public String
	 * updateLocationValues(@ModelAttribute("location") Location location, ModelMap
	 * resModMap) { return null; }
	 */
}
