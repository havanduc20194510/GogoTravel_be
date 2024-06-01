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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tourId")
    private Tour tour;

    @OneToMany(mappedBy = "tourSchedule", cascade = CascadeType.ALL)
    private List<ScheduleDetail> scheduleDetail;

    @OneToOne(mappedBy = "tourSchedule", cascade = CascadeType.ALL)
    private Task task;
}
