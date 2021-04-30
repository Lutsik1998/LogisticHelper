package com.diploma.application.services.impl;

import com.diploma.application.models.Truck;
import com.diploma.application.repositories.TruckRepository;
import com.diploma.application.services.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TruckServiceImpl implements TruckService {

    @Autowired
    private TruckRepository truckRepository;

    @Override
    public List<Truck> findAll() {
        return truckRepository.findAll();
    }

    @Override
    public Truck findTruckById(Long id) {
        return truckRepository.findTruckById(id);
    }



    @Override
    public void saveTruck(Truck truck) {
        truckRepository.save(truck);
    }

    @Override
    public void resetCounterWheels(Truck truck) {
        truck.setCounterWheels(0);
    }

    @Override
    public void resetCounterOil(Truck truck) {
        truck.setCounterOil(0);
    }

    @Override
    public boolean deleteById(Long truckId) {
        if (truckId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "truck" + truckId + "not found");
        }
        Optional<Truck> optionalTruck = truckRepository.findById(truckId);
        if (optionalTruck.isPresent()) {
            Truck truck = optionalTruck.get();
            if (truckRepository.existsById(truckId)) {
                truckRepository.deleteById(truckId);
                if (!truckRepository.existsById(truckId)) {
                    return true;
                }
            }
        }
        return false;
    }


}
