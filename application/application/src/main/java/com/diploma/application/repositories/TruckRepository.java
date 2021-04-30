package com.diploma.application.repositories;

import com.diploma.application.models.Truck;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TruckRepository extends MongoRepository<Truck,Long> {

    Truck findTruckById(Long id);
    Truck findTruckByTruckNumber(String truckNumber);
    boolean existsTruckByTruckNumber(String truckNumber);
    void deleteTruckByTruckNumber(String truckNumber);
    @Query("{ 'truckNumber' : { $regex: ?0 } }")
    List<Truck> findTruckByRegexpNumber(String regexp);









}
