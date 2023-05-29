package com.nymble.nymble.model;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int passengerNumber;

    @Enumerated(EnumType.STRING)
    private PassengerType type;

    private double balance;

    @ManyToMany
    @JoinTable(
            name = "passenger_activity",
            joinColumns = @JoinColumn(name = "passenger_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private List<Activity> activities = new ArrayList<>();

    public Passenger(long l, String jane_smith) {
    }

}
