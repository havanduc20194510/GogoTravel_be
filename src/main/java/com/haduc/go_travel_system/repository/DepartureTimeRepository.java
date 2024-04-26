package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.DepartureTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartureTimeRepository extends JpaRepository<DepartureTime, Long> {
}
