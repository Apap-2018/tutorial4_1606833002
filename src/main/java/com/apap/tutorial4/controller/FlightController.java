package com.apap.tutorial4.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.FlightService;
import com.apap.tutorial4.service.PilotService;

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping(value = "/flight/delete/{id}", method = RequestMethod.POST)
	private String del(@PathVariable(value = "id") Long id, Model model) {
		flightService.deleteFlightById(id);
		return "del";
	}
	
	@RequestMapping(value = "/flight/update/{id}", method = RequestMethod.GET)
	private String update(@PathVariable(value = "id") Long id, Model model) {
		FlightModel flight = flightService.getFlightDetailById(id);
		
		model.addAttribute("flight", flight);
		return "update-flight";
	}
	
	@RequestMapping(value = "/flight/update/{id}", method = RequestMethod.POST)
	private String updateSubmit(@PathVariable(value = "id") Long id,
			@RequestParam(value = "flightNumber") String flightNumber,
			@RequestParam(value = "origin") String origin,
			@RequestParam(value = "destination") String destination,
			@RequestParam(value = "time") Date time,Model model) {
		FlightModel flight = flightService.getFlightDetailById(id);
		flight.setFlightNumber(flightNumber);
		flight.setOrigin(origin);
		flight.setDestination(destination);
		flight.setTime(time);
		flightService.addFlight(flight);
		
		model.addAttribute("flight", flight);
		return "update";
	}

	@RequestMapping("/flight/viewall")
	public String viewall(Model model) {
		List<FlightModel> archive = flightService.getFlightList();
		
		model.addAttribute("flightList", archive);
		return "viewall-flight";
	}
}
