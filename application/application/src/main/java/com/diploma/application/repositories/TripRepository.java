package com.diploma.application.repositories;


import com.diploma.application.models.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TripRepository extends MongoRepository<Trip,Long> {

    Trip findTripById(Long id);
    List<Trip> findTripsByStartPoint(String startPoint);
    List<Trip> findTripsByDestinationPoint(String destinationPoint);
    List<Trip> findTripsByCustomer(String customer);
    List<Trip> findTripsByTruckNumber(String customer);





}
