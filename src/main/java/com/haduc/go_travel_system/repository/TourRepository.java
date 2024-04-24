package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, String> {
    Tour findByTourId(String tourId);

}
