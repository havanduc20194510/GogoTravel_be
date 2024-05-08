package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.request.CreateDepartureTimeRequest;
import com.haduc.go_travel_system.dto.response.DepartureTimeResponse;

import java.util.List;

public interface DepartureTimeService {
    DepartureTimeResponse createDepartureTime(CreateDepartureTimeRequest request);

    DepartureTimeResponse getDepartureTime(Long departureTimeId);

    DepartureTimeResponse updateDepartureTime(CreateDepartureTimeRequest request, Long departureTimeId);

    String deleteDepartureTime(Long departureTimeId);

    List<DepartureTimeResponse> getDepartureTimeByTourId(String tourId);
}
