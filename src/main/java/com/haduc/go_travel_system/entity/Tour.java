package com.haduc.go_travel_system.entity;

import com.haduc.go_travel_system.enums.TourStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String tourId;
    private String name;
    private Double babyPrice;
    private Double adultPrice;
    private Double childPrice;
    private String unit;
    private String description;
    private Long numberOfDays;
    private Long numberOfNights;
    private LocalDate startDate;
    private LocalDate endDate;
    private String vehicle;
    private String departure;
    private Double hotelStar;
    private Long numberOfSeats;
    private Long availableSeats;
    private Double rating;
    @Enumerated(EnumType.STRING)
    private TourStatus status;
    private String note;
    @ManyToOne
    @JoinColumn(name = "tourTypeId")
    private TourType tourType;
    @OneToMany(mappedBy = "tour")
    private List<TourImage> images;
    @OneToMany(mappedBy = "tour")

    private List<TourSchedule> schedules;
}
