package com.diploma.application.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Document(collection = "trips" )
public class Trip {

    @Transient
    public static final String SEQUENCE_NAME = "trips_sequence";

    @Id
    private long id;
    private String customer;
    @JsonFormat(pattern = "dd.MM.yyyy")
    @Schema(pattern = "dd.MM.yyyy")
    private LocalDate dateOfLoading;
    @JsonFormat(pattern = "dd.MM.yyyy")
    @Schema(pattern = "dd.MM.yyyy")
    private LocalDate dateOfUnLoading;
    private String startPoint;
    private String destinationPoint;
    private int distance;
    private float price;
    private float priceWithoutVAT;
    private float VAT;
    private int dfConsumed;
    private float driversSalary;
    private String truckNumber;
    private float pricePerKm;
    private float expenses;
    private float netProfit;
    private float dieselPrice;


    public Trip(String customer, LocalDate dateOfLoading, LocalDate dateOfUnLoading, String startPoint, String destinationPoint, int distance, float price, float priceWithoutVAT, float VAT, int dfConsumed, float driversSalary, String truckNumber, float pricePerKm, float expenses, float netProfit, float dieselPrice) {
        this.customer = customer;
        this.dateOfLoading = dateOfLoading;
        this.dateOfUnLoading = dateOfUnLoading;
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
        this.distance = distance;
        this.price = price;
        this.priceWithoutVAT = priceWithoutVAT;
        this.VAT = VAT;
        this.dfConsumed = dfConsumed;
        this.driversSalary = driversSalary;
        this.truckNumber = truckNumber;
        this.pricePerKm = pricePerKm;
        this.expenses = expenses;
        this.netProfit = netProfit;
        this.dieselPrice = dieselPrice;
    }

    public Trip(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public LocalDate getDateOfLoading() {
        return dateOfLoading;
    }

    public void setDateOfLoading(LocalDate dateOfLoading) {
        this.dateOfLoading = dateOfLoading;
    }

    public LocalDate getDateOfUnLoading() {
        return dateOfUnLoading;
    }

    public void setDateOfUnLoading(LocalDate dateOfUnLoading) {
        this.dateOfUnLoading = dateOfUnLoading;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPriceWithoutVAT() {
        return priceWithoutVAT;
    }

    public void setPriceWithoutVAT(float priceWithoutVAT) {
        this.priceWithoutVAT = priceWithoutVAT;
    }

    public float getVAT() {
        return VAT;
    }

    public void setVAT(float VAT) {
        this.VAT = VAT;
    }

    public int getDfConsumed() {
        return dfConsumed;
    }

    public void setDfConsumed(int dfConsumed) {
        this.dfConsumed = dfConsumed;
    }

    public float getDriversSalary() {
        return driversSalary;
    }

    public void setDriversSalary(float driversSalary) {
        this.driversSalary = driversSalary;
    }

    public String getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    public float getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(float pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public float getExpenses() {
        return expenses;
    }

    public void setExpenses(float expenses) {
        this.expenses = expenses;
    }

    public float getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(float netProfit) {
        this.netProfit = netProfit;
    }

    public float getDieselPrice() {
        return dieselPrice;
    }

    public void setDieselPrice(float dieselPrice) {
        this.dieselPrice = dieselPrice;
    }




    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
