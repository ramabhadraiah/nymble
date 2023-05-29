package com.nymble.nymble.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double cost;
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @ManyToMany(mappedBy = "activities")
    private List<Passenger> passengers = new ArrayList<>();

    public Activity(long l, String s) {
    }


    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

}

