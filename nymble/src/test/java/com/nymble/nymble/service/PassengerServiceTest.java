package com.nymble.nymble.service;

import com.nymble.nymble.model.Activity;
import com.nymble.nymble.model.Passenger;
import com.nymble.nymble.model.PassengerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PassengerServiceTest {

    private PassengerService passengerService;
    private Activity activity;

    @BeforeEach
    void setup() {
        activity = mock(Activity.class);
        passengerService = new PassengerService(activity);
    }

    @Test
    void getAllPassengers() {

        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger(1L, "John Doe"));
        passengers.add(new Passenger(2L, "Jane Smith"));


        List<Passenger> result = passengerService.getAllPassengers();


        assertEquals(passengers, result);
    }

    @Test
    void getPassengerById() {

        Long passengerId = 1L;
        Passenger passenger = new Passenger(passengerId, "John Doe");
        passengerService.addPassenger(passenger);


        Passenger result = passengerService.getPassengerById(passengerId);


        assertEquals(passenger, result);
    }


    @Test
    void addPassenger() {

        Passenger passenger = new Passenger(1L, "John Doe");


        passengerService.addPassenger(passenger);


        List<Passenger> result = passengerService.getAllPassengers();
        assertEquals(1, result.size());
        assertEquals(passenger, result.get(0));
    }

    @Test
    void signUpForActivity_StandardPassenger() {

        Passenger passenger = new Passenger(1L, "John Doe");
        passenger.setType(PassengerType.STANDARD);
        passenger.setBalance(50);
        Activity activity = new Activity(1L, "Activity 1");
        activity.setCost(100);

        assertThrows(RuntimeException.class, () -> passengerService.signUpForActivity(passenger, activity));
    }

    @Test
    void signUpForActivity_StandardPassenger_SufficientBalance() {

        Passenger passenger = new Passenger(1L, "John Doe");
        passenger.setType(PassengerType.STANDARD);
        passenger.setBalance(100);
        Activity activity = new Activity(1L, "Activity 1");
        activity.setCost(50);


        passengerService.signUpForActivity(passenger, activity);


        verify(activity, times(1)).addPassenger(passenger);
        assertEquals(50, passenger.getBalance());
    }

    @Test
    void signUpForActivity_GoldPassenger_InsufficientBalance() {

        Passenger passenger = new Passenger(1L, "John Doe");
        passenger.setType(PassengerType.GOLD);
        passenger.setBalance(80);
        Activity activity = new Activity(1L, "Activity 1");
        activity.setCost(100);

        assertThrows(RuntimeException.class, () -> passengerService.signUpForActivity(passenger, activity));
    }

    @Test
    void signUpForActivity_PremiumPassenger() {
        Passenger passenger = new Passenger(1L, "John Doe");
        passenger.setType(PassengerType.PREMIUM);
        passenger.setBalance(100);
        Activity activity = new Activity(1L, "Activity 1");
        activity.setCost(100);

        passengerService.signUpForActivity(passenger, activity);

        verify(activity, times(1)).addPassenger(passenger);
        assertEquals(100, passenger.getBalance());
    }
}
