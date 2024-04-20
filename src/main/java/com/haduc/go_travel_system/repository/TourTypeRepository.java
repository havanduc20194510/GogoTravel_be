package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.TourType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourTypeRepository extends JpaRepository<TourType, Long> {
    TourType findByName(String tourType);
}
