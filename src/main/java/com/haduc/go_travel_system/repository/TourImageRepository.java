package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.TourImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourImageRepository extends JpaRepository<TourImage, Long> {
    List<TourImage> findByTourTourId(String tourId);
}
