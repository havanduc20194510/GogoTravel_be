package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.DepartureTime;
import com.haduc.go_travel_system.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DepartureTimeRepository extends JpaRepository<DepartureTime, Long> {
    boolean existsByTourAndStartDate(Tour tour, LocalDate startDate);
    List<DepartureTime> findByTourTourId(String tourId);

    DepartureTime findByTourTourIdAndStartDate(String tourId, LocalDate startDate);
}
