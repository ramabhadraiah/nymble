package com.nymble.nymble.service;

import com.nymble.nymble.model.Activity;
import com.nymble.nymble.repositories.ActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ActivityServiceTest {

    @Mock
    private ActivityRepository activityRepository;

    private ActivityService activityService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        activityService = new ActivityService(activityRepository);
    }

    @Test
    void getAllActivities() {

        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity(1L, "Activity 1"));
        activities.add(new Activity(2L, "Activity 2"));

        List<Activity> result = activityService.getAllActivities();

        assertEquals(activities, result);
    }

    @Test
    void addActivity() {

        Activity activity = new Activity(1L, "Activity 1");


        activityService.addActivity(activity);


        List<Activity> result = activityService.getAllActivities();
        assertEquals(1, result.size());
        assertEquals(activity, result.get(0));
    }

    @Test
    void getActivitiesWithAvailableSpaces() {

        int count = 0; // Number of available spaces
        List<Activity> expectedActivities = new ArrayList<>();
        expectedActivities.add(new Activity(1L, "Activity 1"));
        expectedActivities.add(new Activity(2L, "Activity 2"));

        when(activityRepository.findByCapacityGreaterThan(count)).thenReturn(expectedActivities);


        List<Activity> result = activityService.getActivitiesWithAvailableSpaces();


        assertEquals(expectedActivities, result);
        verify(activityRepository, times(1)).findByCapacityGreaterThan(count);
    }

    @Test
    void getActivityById() {

        Long activityId = 1L;
        Activity activity = new Activity(activityId, "Activity 1");
        activityService.addActivity(activity);
        Activity result = activityService.getActivityById(activityId);

        assertEquals(activity, result);
    }

}
