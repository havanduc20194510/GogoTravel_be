package com.haduc.go_travel_system.entity;

import com.haduc.go_travel_system.enums.BookingStatus;
import com.haduc.go_travel_system.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTask {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    private User user;
    private String email;
    private String phone;
    @ManyToOne
    private Tour tour;
    @ManyToOne
    private BookingTour bookingTour;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
    private LocalDate taskDeadline;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
}
