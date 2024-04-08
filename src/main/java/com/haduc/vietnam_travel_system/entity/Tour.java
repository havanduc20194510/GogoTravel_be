package com.haduc.vietnam_travel_system.entity;

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
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String tourId;
    private String name;
    private Double price;
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
    private String status;
    private String image;
    private String note;
    @OneToOne
    @JoinColumn(name = "tourTypeId")
    private TourType tourType;
}
