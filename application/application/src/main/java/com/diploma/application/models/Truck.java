package com.diploma.application.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trucks" )
public class Truck {


    @Id
    private long id;
    private String model;
    private String truckNumber;
    private float carMileage;
    private float counterOil;
    private float counterWheels;

    @Transient
    public static final String SEQUENCE_NAME = "trucks_sequence";

    public Truck(String model, String truckNumber, int carMileage, float counterOil, float counterWheels) {
        this.model = model;
        this.truckNumber = truckNumber;
        this.carMileage = carMileage;
        this.counterOil = counterOil;
        this.counterWheels = counterWheels;
    }

    public Truck(){

    }
    public float getCounterOil() {
        return counterOil;
    }

    public void setCounterOil(float counterOil) {
        this.counterOil = counterOil;
    }

    public float getCounterWheels() {
        return counterWheels;
    }

    public void setCounterWheels(float counterWheels) {
        this.counterWheels = counterWheels;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    public float getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(float carMileage) {
        this.carMileage = carMileage;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
