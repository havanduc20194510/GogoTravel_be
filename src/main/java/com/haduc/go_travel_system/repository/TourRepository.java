package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, String>, PagingAndSortingRepository<Tour, String> {
    List<Tour> findByNameContainsIgnoreCaseAndDepartureLocationContainingIgnoreCaseAndDepartureTimesStartDateGreaterThanAndNumberOfDaysLessThanEqual(String destination, String departureLocation, LocalDate startDate, Long numberOfDay);
    Page<Tour> findByNameContainsIgnoreCaseAndDepartureLocationContainingIgnoreCaseAndDepartureTimesStartDateGreaterThanAndNumberOfDaysLessThanEqual(String destination, String departureLocation, LocalDate startDate, Long numberOfDay, Pageable pageable);
}
