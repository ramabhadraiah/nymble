package com.nymble.nymble.model;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "travel_package")
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "passenger_capacity")
    private int passengerCapacity;

    @OneToMany(mappedBy = "travelPackage", cascade = CascadeType.ALL)
    private List<Destination> destinations = new ArrayList<>();

    @OneToMany(mappedBy = "travelPackage", cascade = CascadeType.ALL)
    private List<Passenger> passengers = new ArrayList<>();
}