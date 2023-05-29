package com.nymble.nymble.controller;

import com.nymble.nymble.model.Activity;
import com.nymble.nymble.service.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivityControllerTest {
    @Mock
    private ActivityService activityService;
    @Mock
    private Model model;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        activityController = new ActivityController(activityService);
    }
    private ActivityController activityController;

    @Test
    void getAllActivities() {
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity(1L, "Activity 1"));
        activities.add(new Activity(2L, "Activity 2"));

        when(activityService.getAllActivities()).thenReturn(activities);

        String viewName = activityController.getAllActivities(model);

        assertEquals("activity-list", viewName);
        verify(model, times(1)).addAttribute("activities", activities);
    }

    @Test
    void addActivity() {
        Activity activity = new Activity(1L, "Activity 1");

        // Act
        String redirectUrl = activityController.addActivity(activity);

        // Assert
        assertEquals("redirect:/activities", redirectUrl);
        verify(activityService, times(1)).addActivity(activity);
    }

    @Test
    void getActivitiesWithAvailableSpaces() {
        List<Activity> availableActivities = new ArrayList<>();
        availableActivities.add(new Activity(1L, "Activity 1"));
        availableActivities.add(new Activity(2L, "Activity 2"));

        when(activityService.getActivitiesWithAvailableSpaces()).thenReturn(availableActivities);

        // Act
        String viewName = activityController.getActivitiesWithAvailableSpaces(model);

        // Assert
        assertEquals("activity-available-list", viewName);
        verify(model, times(1)).addAttribute("activities", availableActivities);
    }

}