package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.BookingTour;
import com.haduc.go_travel_system.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingTourRepository extends JpaRepository<BookingTour, String> {
    List<BookingTour> findByUserIdOrderByBookingDate(String userId);
    List<BookingTour> findByTourTourId(String tourId);
    List<BookingTour> findByEmailAndStatus(String email, BookingStatus status);
    boolean existsByTourTourIdAndUserId(String tourId, String userId);

    List<BookingTour> findByStatus(BookingStatus status);
}
