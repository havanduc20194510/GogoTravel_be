package com.haduc.go_travel_system.entity;

import com.haduc.go_travel_system.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tourScheduleId", referencedColumnName = "id")
    private TourSchedule tourSchedule;
    private String name;
    private String description;
    private Long coin;
    private String reward;
    private LocalDate deadline;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taskTypeId")
    private TaskType taskType;
}
