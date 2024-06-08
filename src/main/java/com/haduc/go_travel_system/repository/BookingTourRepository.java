package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.BookingTour;
import com.haduc.go_travel_system.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingTourRepository extends JpaRepository<BookingTour, String> {
    List<BookingTour> findByUserIdOrderByBookingDateDesc(String userId);
    List<BookingTour> findByTourTourId(String tourId);
    boolean existsByTourTourIdAndUserIdAndStatus(String tourId, String userId, BookingStatus status);

    List<BookingTour> findByStatus(BookingStatus status);

    List<BookingTour> findByPhoneOrEmail(String phone, String email);

    @Query("SELECT MONTH(b.startDate) as month, SUM(b.total) as total " +
            "FROM BookingTour b " +
            "WHERE b.status = :status AND YEAR(b.bookingDate) = :year " +
            "GROUP BY MONTH(b.startDate)")
    List<Object[]> findMonthlyTotalByStatusAndYear(BookingStatus status, int year);


    @Query("SELECT MONTH(bt.startDate) AS month, " +
            "SUM(bt.numberOfAdults + bt.numberOfChildren + bt.numberOfBabies) AS totalGuests " +
            "FROM BookingTour bt " +
            "WHERE bt.status = :status AND YEAR(bt.bookingDate) = :year AND ( MONTH(bt.startDate) = :month OR MONTH(bt.startDate) = (:month - 1))" +
            "GROUP BY MONTH(bt.startDate)")
    List<Object[]> getTotalGuestsByMonth(BookingStatus status, int year, int month);

}
