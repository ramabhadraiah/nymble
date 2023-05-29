package com.nymble.nymble.controller;
import com.nymble.nymble.model.Activity;
import com.nymble.nymble.model.Passenger;
import com.nymble.nymble.service.ActivityService;
import com.nymble.nymble.service.PassengerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/passengers")
public class PassengerController {
    private final Passenger passenger;
    private final PassengerService passengerService;
    private final ActivityService activityService;

    public PassengerController(PassengerService passengerService, ActivityService activityService) {
        this.passenger = passenger;
        this.passengerService = passengerService;
        this.activityService = activityService;
    }


    @PostMapping("/{passengerId}/activities/{activityId}/signup")
    public String signUpForActivity(
            @PathVariable("passengerId") Long passengerId,
            @PathVariable("activityId") Long activityId,
            Model model
    ) {
        Passenger passenger = passengerService.getPassengerById(passengerId);
        Activity activity = activityService.getActivityById(activityId);

        try {
            passengerService.signUpForActivity(passenger, activity);
            model.addAttribute("message", "Successfully signed up for the activity!");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "redirect:/passengers/{passengerId}";
    }


    @GetMapping
    public String getAllPassengers(Model model) {
        model.addAttribute("passengers", passengerService.getAllPassengers());
        return "passenger-list";
    }

    @GetMapping("/{id}")
    public String getPassengerById(@PathVariable Long id, Model model) {
        Passenger passenger = passengerService.getPassengerById(id);
        model.addAttribute("passenger", passenger);
        return "passenger-details";
    }

    @PostMapping("/add")
    public String addPassenger(@ModelAttribute("passenger") Passenger passenger) {
        passengerService.addPassenger(passenger);
        return "redirect:/passengers";
    }

}
