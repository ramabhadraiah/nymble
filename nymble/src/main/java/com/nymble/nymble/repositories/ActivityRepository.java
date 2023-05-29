package com.nymble.nymble.repositories;


import com.nymble.nymble.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByCapacityGreaterThan(int count);
}
