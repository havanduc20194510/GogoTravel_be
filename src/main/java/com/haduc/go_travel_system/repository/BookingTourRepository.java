package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.BookingTour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingTourRepository extends JpaRepository<BookingTour, String> {
    List<BookingTour> findByUserId(String userId);
}
