package com.apap.tutorial4.service;


import java.util.List;

import com.apap.tutorial4.model.FlightModel;

/**
 * 
 * FlightService
 * @author ivanabdurrahman
 *
 */
public interface FlightService {
	FlightModel getFlightDetailById (Long id);
	
	void addFlight(FlightModel flight);
	
	void deleteFlightById(Long id);
	
	List<FlightModel> getFlightList();
}
