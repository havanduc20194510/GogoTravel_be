package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.TourSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourScheduleRepository extends JpaRepository<TourSchedule, Long> {
}
