package com.haduc.go_travel_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tourScheduleId")
    private TourSchedule tourSchedule;
    private String timeLine;
    private String place;
    private String description;
    private String activity;
    private String image;
    @OneToOne(mappedBy = "scheduleDetail")
    private Task task;
}
