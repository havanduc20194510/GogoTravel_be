package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByBookingUserIdOrderByPayDateDesc(String userId);

    List<Payment> findByEmail(String email);

    List<Payment> findByPhone(String phone);

    boolean existsByBookingId(String bookingId);
}
