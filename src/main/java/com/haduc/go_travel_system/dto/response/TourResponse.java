package com.haduc.go_travel_system.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.haduc.go_travel_system.entity.TourType;
import com.haduc.go_travel_system.enums.TourStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TourResponse {
    private String tourId;
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
    @Enumerated(EnumType.STRING)
    private TourStatus status;
    private String note;
    private Long totalView;
    private TourType tourType;
    private List<TourImageResponse> images;
    private List<DepartureTimeResponse> departureTimes;
    private List<TourScheduleResponse> schedules;
}
