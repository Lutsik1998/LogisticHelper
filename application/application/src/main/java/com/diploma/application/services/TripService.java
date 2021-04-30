package com.diploma.application.services;

import com.diploma.application.models.Trip;

import java.util.List;

public interface TripService {

    List<Trip> findAll();
    Trip saveTrip(Trip trip);
    Trip findTripById(Long id);
    boolean deleteById(Long id);


}
