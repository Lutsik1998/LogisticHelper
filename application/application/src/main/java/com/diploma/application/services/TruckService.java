package com.diploma.application.services;

import com.diploma.application.models.Truck;

import java.util.List;

public interface TruckService {
    List<Truck> findAll();
    Truck findTruckById(Long id);
    void saveTruck(Truck truck);
    boolean deleteById(Long id);
    void resetCounterWheels(Truck truck);
    void resetCounterOil(Truck truck);


}
