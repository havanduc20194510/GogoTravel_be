package com.haduc.go_travel_system.entity;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.*;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartureTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tourId")
    private Tour tour;
    private LocalDate startDate;
    private Long numberOfSeats;

    private Long bookedSeats;

    // dat ten truong trong db la is_available
    @Column(name = "is_available")
    private boolean available;
}
