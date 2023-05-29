package com.nymble.nymble.service;

import com.nymble.nymble.model.Activity;
import com.nymble.nymble.model.Passenger;
import com.nymble.nymble.repositories.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {


    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }
    private final List<Activity> activities = new ArrayList<>();

    public List<Activity> getAllActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivitiesWithAvailableSpaces() {
        int count = 0; // Number of available spaces
        List<Activity> activities = activityRepository.findByCapacityGreaterThan(count);
        return activities;
    }
    public Activity getActivityById(Long id) {
        return activities.stream()
                .filter(activity -> activity.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
