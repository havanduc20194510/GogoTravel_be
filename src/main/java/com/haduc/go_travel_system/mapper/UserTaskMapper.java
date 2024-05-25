package com.haduc.go_travel_system.mapper;

import com.haduc.go_travel_system.dto.request.UserTaskRequest;
import com.haduc.go_travel_system.dto.response.UserTaskResponse;
import com.haduc.go_travel_system.entity.*;
import com.haduc.go_travel_system.repository.BookingTourRepository;
import com.haduc.go_travel_system.repository.TaskRepository;
import com.haduc.go_travel_system.repository.TourRepository;
import com.haduc.go_travel_system.repository.UserRepository;
import lombok.*;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Builder
public class UserTaskMapper {
    private final UserRepository userRepository;
    private final TourRepository tourRepository;
    private final BookingTourRepository bookingTourRepository;

    private final TaskRepository taskRepository;

    public UserTaskResponse toUserTaskResponse(UserTask userTask) {
        return UserTaskResponse.builder()
                .id(userTask.getId())
                .userId(userTask.getUser().getId())
                .email(userTask.getEmail())
                .phone(userTask.getPhone())
                .tourId(userTask.getTour().getTourId())
                .tourName(userTask.getTour().getName())
                .bookingTourId(userTask.getBookingTour().getId())
                .taskDeadline(userTask.getTaskDeadline())
                .taskStatus(userTask.getTaskStatus())
                .build();
    }

    public UserTask toUserTask(UserTaskRequest userTaskRequest) {
        User user = userRepository.findById(userTaskRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Tour tour = tourRepository.findById(userTaskRequest.getTourId())
                .orElseThrow(() -> new RuntimeException("Tour not found"));
        BookingTour bookingTour = bookingTourRepository.findById(userTaskRequest.getBookingTourId())
                .orElseThrow(() -> new RuntimeException("Booking tour not found"));
        Task task = taskRepository.findById(userTaskRequest.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return UserTask.builder()
                .user(user)
                .email(userTaskRequest.getEmail())
                .phone(userTaskRequest.getPhone())
                .tour(tour)
                .bookingTour(bookingTour)
                .task(task)
                .taskDeadline(userTaskRequest.getTaskDeadline())
                .taskStatus(userTaskRequest.getTaskStatus())
                .build();
    }
}
