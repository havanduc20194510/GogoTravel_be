package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, String> {
    List<UserTask> findByEmail(String email);

    List<UserTask> findByPhone(String phone);

    boolean existsByBookingTourId(String bookingTourId);

    List<UserTask> findByTaskStatus(String taskStatus);
}
