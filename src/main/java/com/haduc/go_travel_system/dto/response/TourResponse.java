package com.haduc.go_travel_system.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.haduc.go_travel_system.entity.TourType;
import com.haduc.go_travel_system.enums.TourStatus;
import lombok.*;

import java.util.Date;
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
    private Long availableSeats;
    private TourStatus status;
    private String note;
    private TourType tourType;
    private List<TourImageReponse> images;
    private List<DepartureTimeResponse> departureTimes;
    private List<TourScheduleResponse> schedules;
}
