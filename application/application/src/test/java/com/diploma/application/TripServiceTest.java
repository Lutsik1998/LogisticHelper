package com.diploma.application;



import com.diploma.application.models.Trip;
import com.diploma.application.repositories.TripRepository;
import com.diploma.application.services.TripService;

import com.diploma.application.services.impl.SequenceGeneratorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TripServiceTest {

    @Autowired
    private TripService tripService;

    @Mock
    private TripRepository tripRepository;

    @Autowired
    public void setRepository(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }




    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Test
    public void createTrip() {
        Trip trip = createTestTrip();
        Assert.assertNotNull(trip);
        Trip createdTrip = tripService.saveTrip(trip);
        Assert.assertNotNull(createdTrip);
        assertThat(createdTrip.getCustomer()).isSameAs(trip.getCustomer());
    }

    @Test
    public void findAllTrips() {
        createTrip();
        List<Trip> trips = tripRepository.findAll();
        Assert.assertNotNull(trips);
        Assert.assertTrue(!trips.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void createTripWithNullPointer() {
        Trip trip = new Trip();
        Assert.assertNotNull(trip);
        Trip createdTrip = tripService.saveTrip(trip);
        Assert.assertNotNull(createdTrip);
        assertThat(createdTrip.getCustomer()).isSameAs(trip.getCustomer());
    }

    public Trip createTestTrip(){
        Trip trip = new Trip();
        trip.setId(2222);
        trip.setCustomer("Test");
        trip.setDateOfLoading(LocalDate.of(2000,12,1));
        trip.setDateOfUnLoading(LocalDate.of(2000,12,2));
        trip.setStartPoint("Test");
        trip.setDestinationPoint("Test");
        trip.setDfConsumed(111);
        trip.setPrice(122222);
        trip.setDistance(233);
        trip.setTruckNumber("ax8233bm");
        trip.setDieselPrice(20);
       trip.setPriceWithoutVAT(11112);
       trip.setVAT(22222);
        trip.setDriversSalary(2134);
        trip.setExpenses(5213);
        trip.setNetProfit(23455);
        return trip;
    }
}
