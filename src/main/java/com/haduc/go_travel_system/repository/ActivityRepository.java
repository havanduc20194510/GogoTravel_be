package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
