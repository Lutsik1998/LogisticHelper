package com.diploma.application.controllers;

import com.diploma.application.exeptions.ResourceNotFoundException;
import com.diploma.application.models.Trip;
import com.diploma.application.models.Truck;
import com.diploma.application.repositories.TripRepository;
import com.diploma.application.repositories.TruckRepository;
import com.diploma.application.services.TripService;
import com.diploma.application.services.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    TripService tripService;

    @Autowired
    TruckService truckService;


    @Autowired
    TripRepository tripRepository;

    @Autowired
    TruckRepository truckRepository;

    @GetMapping(value = "")
    public List<Trip> getAllTrips() {
        return tripService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable(value = "id") Long tripId)
            throws ResourceNotFoundException {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found for this id :: " + tripId));
        return ResponseEntity.ok().body(trip);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveTrip(@RequestBody Trip trip){

        Truck truck = truckRepository.findTruckByTruckNumber(trip.getTruckNumber());
        if(truck == null){
            return new ResponseEntity("Bad truck number", HttpStatus.NOT_FOUND);
        }else {
            trip = tripService.saveTrip(trip);
            truck.setCarMileage(truck.getCarMileage()+trip.getDistance());
            truck.setCounterOil(truck.getCounterOil()+trip.getDistance());
            truck.setCounterWheels(truck.getCounterWheels()+trip.getDistance());
            truckService.saveTruck(truck);
            return new ResponseEntity(trip, HttpStatus.CREATED);
        }
    }



    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Truck> deleteTrip(@PathVariable(value = "id") Long tripId) {
        if (tripService.deleteById(tripId)) {
            return new ResponseEntity(null, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }





    @PutMapping("/update/{id}")
    public ResponseEntity<Trip> updateTruck(@PathVariable(value = "id") Long tripId, @RequestBody Trip tripDetails) throws ResourceNotFoundException {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found for this id :: " + tripId));
        trip.setCustomer(tripDetails.getCustomer());
        trip.setDateOfLoading(tripDetails.getDateOfLoading());
        trip.setDateOfUnLoading(tripDetails.getDateOfUnLoading());
        trip.setStartPoint(tripDetails.getStartPoint());
        trip.setDestinationPoint(tripDetails.getDestinationPoint());
        trip.setDistance(tripDetails.getDistance());
        trip.setPrice(tripDetails.getPrice());
        trip.setPriceWithoutVAT(tripDetails.getPriceWithoutVAT());
        trip.setVAT(tripDetails.getVAT());
        trip.setDfConsumed(tripDetails.getDfConsumed());
        trip.setTruckNumber(tripDetails.getTruckNumber());
        trip.setDieselPrice(tripDetails.getDieselPrice());
        float daysBetween = DAYS.between(trip.getDateOfLoading(), trip.getDateOfUnLoading());
        trip.setDriversSalary((1.8f*trip.getDistance())+150*(daysBetween+1));
        trip.setExpenses(trip.getDriversSalary()+(trip.getDfConsumed()*trip.getDieselPrice()));
        trip.setNetProfit(trip.getPriceWithoutVAT()-trip.getExpenses());
        trip.setPricePerKm(trip.getPrice()/trip.getDistance());
        final Trip updatedTrip = tripRepository.save(trip);
        return ResponseEntity.ok(updatedTrip);
    }

}
