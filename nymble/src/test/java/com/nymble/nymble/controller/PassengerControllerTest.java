package com.nymble.nymble.controller;

import com.nymble.nymble.model.Activity;
import com.nymble.nymble.model.Passenger;
import com.nymble.nymble.service.ActivityService;
import com.nymble.nymble.service.PassengerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PassengerControllerTest {

    @Mock
    private PassengerService passengerService;
    @Mock
    private ActivityService activityService;
    @Mock
    private Model model;

    private PassengerController passengerController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        passengerController = new PassengerController(passengerService, activityService);
    }

    @Test
    void SuccessSignUpForActivity() {
        Long passengerId = 1L;
        Long activityId = 1L;
        Passenger passenger = new Passenger(passengerId, "John Doe");
        Activity activity = new Activity(activityId, "Activity 1");

        when(passengerService.getPassengerById(passengerId)).thenReturn(passenger);
        when(activityService.getActivityById(activityId)).thenReturn(activity);

        String redirectUrl = passengerController.signUpForActivity(passengerId, activityId, model);

        assertEquals("redirect:/passengers/" + passengerId, redirectUrl);
        verify(passengerService, times(1)).signUpForActivity(passenger, activity);
        verify(model, times(1)).addAttribute("message", "Successfully signed up for the activity!");
        verifyNoMoreInteractions(model);
    }

    @Test
    void ErrorSignUpForActivity() {
        // Arrange
        Long invalidPassengerId = 999L;
        Long activityId = 1L;
        Activity activity = new Activity(activityId, "Activity 1");

        when(passengerService.getPassengerById(invalidPassengerId)).thenReturn(null);
        when(activityService.getActivityById(activityId)).thenReturn(activity);

        String redirectUrl = passengerController.signUpForActivity(invalidPassengerId, activityId, model);

        assertEquals("redirect:/passengers/" + invalidPassengerId, redirectUrl);
        verify(passengerService, never()).signUpForActivity(any(), any());
        verify(model, times(1)).addAttribute("error", "Passenger not found with ID: " + invalidPassengerId);
        verifyNoMoreInteractions(model);
    }

    @Test
    void getAllPassengers() {
        // Arrange
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger(1L, "John Doe"));
        passengers.add(new Passenger(2L, "Jane Smith"));

        when(passengerService.getAllPassengers()).thenReturn(passengers);

        String viewName = passengerController.getAllPassengers(model);

        assertEquals("passenger-list", viewName);
        verify(model, times(1)).addAttribute("passengers", passengers);
    }

    @Test
    void getPassengerById() {
        Long passengerId = 1L;
        Passenger passenger = new Passenger(passengerId, "John Doe");

        when(passengerService.getPassengerById(passengerId)).thenReturn(passenger);

        String viewName = passengerController.getPassengerById(passengerId, model);

        assertEquals("passenger-details", viewName);
        verify(model, times(1)).addAttribute("passenger", passenger);
    }

    @Test
    void addPassenger() {
        Passenger passenger = new Passenger(1L, "John Doe");

        String redirectUrl = passengerController.addPassenger(passenger);

        assertEquals("redirect:/passengers", redirectUrl);
        verify(passengerService, times(1)).addPassenger(passenger);
    }
}
