package com.haduc.go_travel_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String timeInDays;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tourId")
    private Tour tour;

    @OneToMany(mappedBy = "tourSchedule")
    private List<ScheduleDetail> scheduleDetail;

    @OneToOne(mappedBy = "tourSchedule")
    private Task task;
}
