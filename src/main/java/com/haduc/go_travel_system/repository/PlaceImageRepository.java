package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.Place;
import com.haduc.go_travel_system.entity.PlaceImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceImageRepository extends JpaRepository<PlaceImage, Long> {
    List<PlaceImage> findByPlaceId(Long placeId);
}
