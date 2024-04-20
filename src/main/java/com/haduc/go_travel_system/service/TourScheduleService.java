package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.request.CreateTourScheduleRequest;
import com.haduc.go_travel_system.dto.response.TourScheduleResponse;

import java.util.List;

public interface TourScheduleService {
    TourScheduleResponse createTourSchedule(CreateTourScheduleRequest request);
    TourScheduleResponse updateTourSchedule(CreateTourScheduleRequest request);
    String deleteTourSchedule(Long id);
    List<TourScheduleResponse> getTourSchedule(Long tourId);
}
