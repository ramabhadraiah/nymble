package com.nymble.nymble.service;

import com.nymble.nymble.model.Activity;
import com.nymble.nymble.model.Passenger;
import com.nymble.nymble.model.PassengerType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerService {
    private final Activity activity;
    private final List<Passenger> passengers = new ArrayList<>();

    public PassengerService(Activity activity) {
        this.activity = activity;
    }

    public List<Passenger> getAllPassengers() {
        return passengers;
    }
    public Passenger getPassengerById(Long id) {
        return passengers.stream()
                .filter(passenger -> passenger.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public void signUpForActivity(Passenger passenger, Activity activity) {
        if (passenger.getType() == PassengerType.STANDARD) {
            if (passenger.getBalance() < activity.getCost()) {
                throw new RuntimeException("Insufficient balance to sign up for the activity.");
            }
            passenger.setBalance(passenger.getBalance() - activity.getCost());
        } else if (passenger.getType() == PassengerType.GOLD) {
            double discountedCost = activity.getCost() * 0.9;
            if (passenger.getBalance() < discountedCost) {
                throw new RuntimeException("Insufficient balance to sign up for the activity.");
            }
            passenger.setBalance(passenger.getBalance() - discountedCost);
        } else if (passenger.getType() == PassengerType.PREMIUM) {
            return;
        }

        activity.addPassenger(passenger);
    }


}
