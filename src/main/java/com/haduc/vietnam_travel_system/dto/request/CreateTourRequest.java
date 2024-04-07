package com.haduc.vietnam_travel_system.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTourRequest {
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
    private String status;
    private String image;
    private String note;
    private Long tourTypeId;
}
