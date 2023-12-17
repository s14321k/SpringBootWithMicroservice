package com.sarath.location.controller;

import com.sarath.location.entities.Location;
import com.sarath.location.repository.LocationRepo;
import com.sarath.location.service.LocationService;
import com.sarath.location.util.EmailUtil;

import com.sarath.location.util.ReportUtil;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//	https://reflectoring.io/spring-cors/ CrossOrigin 
//@CrossOrigin(maxAge = 3600)


@Controller // -> This returns the CreateLocation.jsp page in return
//@RestController -> This returns only the name CreateLocation in return


@RequestMapping("LocCon")
public class LocationController
{
	@Autowired
    LocationService locationService;
	
//	@Autowired
//	EmailUtil emailUtil;

    @Autowired
    LocationRepo locationRepo;

    @Autowired
    ReportUtil reportUtil;

    @Autowired
    ServletContext servletContext;

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
//        emailUtil.sendMail("spring69boot@gmail.com", "Saved success", "Hi, welcome back again buddy");
        return "CreateLocation";
    }

    @GetMapping("/listAllLocations")
    public String displayLocations(ModelMap resModMap)
    {
    	List<Location> listLocation = locationService.getAllLocation();
    	generateReport();
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
        generateReport();
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
    
    //@CrossOrigin(origins = "http://localhost:8080")
	@PutMapping("/updateLocationValues") 
	public String updateLocationValues(@ModelAttribute("location") Location location, ModelMap resModMap) {
        locationService.updateLocation(location);
        List<Location> allLocations = locationService.getAllLocation();
        generateReport();
        resModMap.addAttribute("allLocations", allLocations);
        return "displayAllLocations";
    }
	/*
	 * @RequestMapping("/updateLocationValues") public String
	 * updateLocationValues(@ModelAttribute("location") Location location, ModelMap
	 * resModMap) { return null; }
	 */

    @RequestMapping("/generateReport")
    public String generateReport()
    {
        String path = servletContext.getRealPath("/");
        List<Object[]> data = locationRepo.findTypeAndTypeCount();
        reportUtil.generatePieChart(path, data);
        return "Report";
    }
}
