package com.akshay.location.controllers;

import java.util.List;

import org.apache.catalina.servlet4preview.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.akshay.location.entities.Location;
import com.akshay.location.repos.LocationRepository;
import com.akshay.location.service.LocationService;
import com.akshay.location.util.EmailUtil;
import com.akshay.location.util.ReportUtil;

@Controller
public class LocationController {
	@Autowired
	LocationService locationService;
	@Autowired
	LocationRepository repo;
	@Autowired
	EmailUtil emailUtil;
	@Autowired
	ReportUtil reportUtil;
	@Autowired
	ServletContext sc;

	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation";
	}

	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		Location locationSaved = locationService.saveLocation(location);
		String msg = "Location saved with id: " + locationSaved.getId();
		modelMap.addAttribute("msg", msg);
		emailUtil.sendEmail("akshay.py@gmail.com", "Location created", "Location saved with id : " + locationSaved.getId());
		return "createLocation";
	}

	@RequestMapping("/displayLocations")
	public String displayLocations(ModelMap modelMap) {
		List<Location> locations = locationService.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}

	@RequestMapping("/deleteLocation")
	public String deleteLocation(@RequestParam("id") int id, ModelMap modelMap) {
		Location location = locationService.getLocationById(id);
		locationService.deleteLocation(location);
		List<Location> locations = locationService.getAllLocations();
		modelMap.addAttribute("locations", locations);
		emailUtil.sendEmail("akshay.py@gmail.com", "Location deleted", "Location deleted with id : " + id);
		return "displayLocations";
	}

	@RequestMapping("/updateLocation")
	public String showUpdate(@RequestParam("id") int id, ModelMap modelMap) {
		Location location = locationService.getLocationById(id);
		modelMap.addAttribute("location", location);
		return "editLocation";
	}

	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		locationService.saveLocation(location);
		List<Location> locations = locationService.getAllLocations();
		modelMap.addAttribute("locations", locations);
		emailUtil.sendEmail("akshay.py@gmail.com", "Location updated", "Location updated with id : " + location.getId());
		return "displayLocations";
	}

	@RequestMapping("/generateReport")
	public String generateReport() {
		List<Object[]> data = repo.findTypeAndTypeCount();
		String path = sc.getRealPath("/");
		reportUtil.generatePieChart(path, data);
		return "report";
	}
}
