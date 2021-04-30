package com.diploma.application;

import com.diploma.application.services.impl.SequenceGeneratorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import com.diploma.application.models.Truck;
import com.diploma.application.repositories.TruckRepository;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TruckControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private TruckRepository truckRepository;

    @Test
    public void whenCreateTruck_thenStatus201() {

        Truck truck = new Truck("Man","testCreate",1222,2333,1233);

        ResponseEntity<Truck> response = restTemplate.postForEntity("/trucks/save", truck, Truck.class);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(response.getBody().getId(), notNullValue());
        assertThat(response.getBody().getTruckNumber(), is("testCreate"));
    }



    @Test
    public void givenTruck_whenGetTruck_thenStatus200() {

        long id = createTestTruck("Man","testGet",1222,2333,1233).getId();

        Truck truck = restTemplate.getForObject("/trucks/{id}", Truck.class, id);
        assertThat(truck.getTruckNumber(), is("testGet"));
    }
    @Test
    public void whenUpdateTruck_thenStatus200() {

        long id = createTestTruck("Man","testUpdate",1222,2333,1233).getId();
        Truck truck = new Truck("Scania","updated",1222,2333,1233);
        HttpEntity<Truck> entity = new HttpEntity<Truck>(truck);

        ResponseEntity<Truck> response = restTemplate.exchange("/trucks/update/{id}", HttpMethod.PUT, entity, Truck.class,
                id);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getId(), notNullValue());
        assertThat(response.getBody().getModel(), is("Scania"));
    }

    private Truck createTestTruck(String model,String truckNumber, int carMileage, float counterOil, float counterWheels ) {
        Truck truck = new Truck(model,truckNumber,carMileage,counterOil,counterWheels);
        truck.setId(sequenceGeneratorService.generateSequence(Truck.SEQUENCE_NAME));
        return truckRepository.save(truck);
    }

    @Test
    public void whenDeletedTruck_thenStatus200() {

        long id = createTestTruck("Man","testDelete",1222,2333,1233).getId();

        ResponseEntity<Truck> response = restTemplate.exchange("/trucks/delete/{id}", HttpMethod.DELETE, null, Truck.class,
                id);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));


    }

    @Test
    public void whenGetTrucks_thenStatus200() {
        createTestTruck("Man1","getTruck1",1222,2333,1233);
        createTestTruck("Man2","getTruck2",12221,5553,1233).getId();
        ResponseEntity<List<Truck>> response = restTemplate.exchange("/trucks", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Truck>>() {
                });
        List<Truck> trucks = response.getBody();

        assertThat(trucks.get(trucks.size()-1).getModel(), is("Man2"));
    }

}
