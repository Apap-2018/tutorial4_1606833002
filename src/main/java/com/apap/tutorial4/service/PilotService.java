package com.apap.tutorial4.service;

import com.apap.tutorial4.model.PilotModel;

/**
 * 
 * PilotService
 * @author ivanabdurrahman
 *
 */
public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber (String licenseNumber);
	
	void addPilot (PilotModel pilot);
	
	void deletePilotById(Long id);
}
