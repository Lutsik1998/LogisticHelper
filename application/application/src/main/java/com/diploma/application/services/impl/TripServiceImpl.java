package com.diploma.application.services.impl;

import com.diploma.application.models.Trip;
import com.diploma.application.repositories.TripRepository;
import com.diploma.application.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;


@Service
public class TripServiceImpl implements TripService {


    @Autowired
    TripRepository tripRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    @Override
    public Trip saveTrip(Trip trip) {
        trip.setId(sequenceGeneratorService.generateSequence(Trip.SEQUENCE_NAME));
        float daysBetween = DAYS.between(trip.getDateOfLoading(), trip.getDateOfUnLoading());
        trip.setDriversSalary((1.8f*trip.getDistance())+150*(daysBetween+1));
        trip.setExpenses(trip.getDriversSalary()+(trip.getDfConsumed()*trip.getDieselPrice()));
        trip.setPriceWithoutVAT(trip.getPrice()/1.2f);
        trip.setVAT(trip.getPrice()-trip.getPriceWithoutVAT());
        trip.setNetProfit(trip.getPrice()-trip.getExpenses());
        trip.setPricePerKm(trip.getPrice()/trip.getDistance());
        tripRepository.save(trip);
        return trip;
    }

    @Override
    public Trip findTripById(Long id) {
        return tripRepository.findTripById(id);
    }



    @Override
    public boolean deleteById(Long tripId) {

        if (tripId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "trip" + tripId + "not found");
        }
        Optional<Trip> optionalTrip = tripRepository.findById(tripId);
        if (optionalTrip.isPresent()) {
            Trip trip = optionalTrip.get();
            if (tripRepository.existsById(tripId)) {
                tripRepository.deleteById(tripId);
                if (!tripRepository.existsById(tripId)) {
                    return true;
                }
            }
        }
        return false;
    }
}
