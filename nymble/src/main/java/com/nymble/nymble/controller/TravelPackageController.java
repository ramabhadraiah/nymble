package com.nymble.nymble.controller;

import com.nymble.nymble.model.TravelPackage;
import com.nymble.nymble.service.TravelPackageService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/travel-packages")
public class TravelPackageController {
    private final TravelPackageService travelPackageService;

    public TravelPackageController(TravelPackageService travelPackageService) {
        this.travelPackageService = travelPackageService;
    }

    @GetMapping("/{id}/itinerary")
    public String printItinerary(@PathVariable("id") Long travelPackageId, Model model) {
        Optional<TravelPackage> travelPackage = travelPackageService.getTravelPackageWithDetails(travelPackageId);
        model.addAttribute("travelPackage", travelPackage);
        return "itinerary";
    }

    @GetMapping("/{id}/passenger-list")
    public String printPassengerList(@PathVariable("id") Long travelPackageId, Model model) {
        TravelPackage travelPackage = travelPackageService.getTravelPackageWithPassengers(travelPackageId);
        model.addAttribute("travelPackage", travelPackage);
        return "passenger-list";
    }
}

