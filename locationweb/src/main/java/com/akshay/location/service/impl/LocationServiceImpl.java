package com.akshay.location.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshay.location.entities.Location;
import com.akshay.location.repos.LocationRepository;
import com.akshay.location.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	LocationRepository locationRepository;

	@Override
	public Location saveLocation(Location location) {
		locationRepository.save(location);
		return location;
	}

	@Override
	public Location updateLocation(Location location) {
		locationRepository.save(location);
		return location;
	}

	@Override
	public void deleteLocation(Location location) {
		locationRepository.delete(location);
	}

	@Override
	public Location getLocationById(int id) {
		Location location = locationRepository.findById(id).get();
		return location;
	}

	@Override
	public List<Location> getAllLocations() {
		List<Location> locationList = locationRepository.findAll();
		return locationList;
	}

}
