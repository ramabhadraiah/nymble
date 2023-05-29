package com.nymble.nymble.controller;


import com.nymble.nymble.model.Activity;
import com.nymble.nymble.service.ActivityService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public String getAllActivities(Model model) {
        List<Activity> activities = activityService.getAllActivities();
        model.addAttribute("activities", activities);
        return "activity-list";
    }

    @PostMapping
    public String addActivity(Activity activity) {
        activityService.addActivity(activity);
        return "redirect:/activities";
    }

    @GetMapping("/available")
    public String getActivitiesWithAvailableSpaces(Model model) {
        List<Activity> activities = activityService.getActivitiesWithAvailableSpaces();
        model.addAttribute("activities", activities);
        return "activity-available-list";
    }

}

