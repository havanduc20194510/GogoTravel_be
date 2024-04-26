package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.request.CreateDepartureTimeRequest;
import com.haduc.go_travel_system.dto.response.DepartureTimeResponse;

public interface DepartureTimeService {
    DepartureTimeResponse createDepartureTime(CreateDepartureTimeRequest request);
}
