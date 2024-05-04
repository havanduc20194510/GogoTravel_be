package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.dto.response.PlaceResponse;
import com.haduc.go_travel_system.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long>, PagingAndSortingRepository<Place,Long> {
    List<Place> findByName(String placeName);

    List<Place> findByLocation(String city);
}
