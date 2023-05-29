package com.nymble.nymble.model;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "destination")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private TravelPackage travelPackage;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<Activity> activities = new ArrayList<>();

}

