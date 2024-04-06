package com.haduc.vietnam_travel_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tour {
    @Id
    private String tourId;
    private String name;
    private Double price;
    private Double adultPrice;
    private Double childPrice;
    private String unit;
    private String description;
    private Long numberOfDays;
    private Long numberOfNights;
    private Date startDate;
    private Date endDate;
    private String vehicle;
    private String departure;
    private Double hotelStar;
    private Long numberOfSeats;
    private Long availableSeats;
    private String image;
    @OneToOne
    @JoinColumn(name = "tourTypeId")
    private TourType tourType;
    private String note;
}
