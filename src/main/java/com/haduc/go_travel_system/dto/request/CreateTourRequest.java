package com.haduc.go_travel_system.dto.request;

import com.haduc.go_travel_system.enums.TourStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTourRequest {
    private String name;
    private Double adultPrice;
    private Double childPrice;
    private Double babyPrice;
    private String unit;
    private String description;
    private Long numberOfDays;
    private Long numberOfNights;
    private String vehicle;
    private String departureLocation;
    private Double hotelStar;
    private Long numberOfSeats;
    private Long availableSeats;
    @Enumerated(EnumType.STRING)
    private TourStatus status;
    private String note;
    private String tourTypeName;
}
