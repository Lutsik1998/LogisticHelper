package com.diploma.application.controllers;

import com.diploma.application.exeptions.ResourceNotFoundException;
import com.diploma.application.models.Truck;
import com.diploma.application.repositories.TruckRepository;
import com.diploma.application.services.TruckService;
import com.diploma.application.services.impl.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/trucks")
public class TruckController {

    @Autowired
    private TruckService truckService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private TruckRepository truckRepository;


    @GetMapping(value = "")
    public List<Truck> getAllTrucks() {
        return truckService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Truck> getTruckById(@PathVariable(value = "id") Long truckId)
            throws ResourceNotFoundException {
        Truck truck = truckRepository.findById(truckId)
                .orElseThrow(() -> new ResourceNotFoundException("Truck not found for this id :: " + truckId));
        return ResponseEntity.ok().body(truck);
    }


    @PostMapping(value = "/save")
    public ResponseEntity<?> saveTruck(@RequestBody Truck truck) {
        String checkNumber = truck.getTruckNumber();
        if(truckRepository.existsTruckByTruckNumber(checkNumber)){
            return new ResponseEntity("Truck with number " + checkNumber + " already exist", HttpStatus.BAD_REQUEST);
        }else {
            truck.setId(sequenceGeneratorService.generateSequence(Truck.SEQUENCE_NAME));
            truckService.saveTruck(truck);
            return new ResponseEntity(truck, HttpStatus.CREATED);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Truck> updateTruck(@PathVariable(value = "id") Long truckId, @RequestBody Truck truckDetails) throws ResourceNotFoundException {
        Truck truck = truckRepository.findById(truckId)
                .orElseThrow(() -> new ResourceNotFoundException("Truck not found for this id: " + truckId));

        truck.setModel(truckDetails.getModel());
        truck.setTruckNumber(truckDetails.getTruckNumber());
        truck.setCarMileage(truckDetails.getCarMileage());
        truck.setCounterOil(truckDetails.getCounterOil());
        truck.setCounterWheels(truckDetails.getCounterWheels());
        final Truck updatedTruck = truckRepository.save(truck);
        return ResponseEntity.ok(updatedTruck);
    }

    @GetMapping(value = "/trucksByNumberRgx", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> getTrucks(@RequestParam String numberRgx) {
        List<Truck> trucks = truckRepository.findTruckByRegexpNumber("^" + numberRgx);
        Map<String, String> trucksNumbers = new HashMap<>();
        if (trucks == null) {
            return new ResponseEntity("Trucks not found", HttpStatus.BAD_REQUEST);
        } else {
            for (Truck truck : trucks) {
                trucksNumbers.put(Long.toString(truck.getId()), truck.getTruckNumber());
            }
        }
        return new ResponseEntity<Map<String, String>>(trucksNumbers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/del/{truckNumber}")
    public ResponseEntity<Truck> deleteTruckByNum(@PathVariable String truckNumber) {
        if (truckRepository.existsTruckByTruckNumber(truckNumber)) {
            truckRepository.deleteTruckByTruckNumber(truckNumber);
            return new ResponseEntity("Deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }



    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity <Truck > deleteTruckById(@PathVariable(value = "id") Long truckId) throws ResourceNotFoundException {
        if (truckService.deleteById(truckId)) {
            return new ResponseEntity(null , HttpStatus.OK);
        }
        return new ResponseEntity(null , HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/resetWh/{id}")
    public ResponseEntity<Truck> resetWheels(@PathVariable(value = "id") Long truckId) throws ResourceNotFoundException {
        Truck truck = truckRepository.findById(truckId)
                .orElseThrow(() -> new ResourceNotFoundException("Truck not found for this id: " + truckId));
        truckService.resetCounterWheels(truck);
        final Truck updatedTruck = truckRepository.save(truck);
        return ResponseEntity.ok(updatedTruck);
    }

    @PatchMapping("/resetOil/{id}")
    public ResponseEntity<Truck> resetOil(@PathVariable(value = "id") Long truckId) throws ResourceNotFoundException {
        Truck truck = truckRepository.findById(truckId)
                .orElseThrow(() -> new ResourceNotFoundException("Truck not found for this id : " + truckId));
        truckService.resetCounterOil(truck);
        final Truck updatedTruck = truckRepository.save(truck);
        return ResponseEntity.ok(updatedTruck);
    }


}
