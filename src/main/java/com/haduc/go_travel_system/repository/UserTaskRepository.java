package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.UserTask;
import com.haduc.go_travel_system.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, String> {
    List<UserTask> findByEmail(String email);

    List<UserTask> findByPhone(String phone);

    boolean existsByBookingTourId(String bookingTourId);

    List<UserTask> findByTaskStatus(TaskStatus taskStatus);

    List<UserTask> findByUserId(String userId);

    List<UserTask> findByPhoneOrEmail(String phone, String email);

    @Query("SELECT u.taskStatus, COUNT(u) FROM UserTask u GROUP BY u.taskStatus")
    List<Object[]> countTasksByStatus();
}
