package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.request.CreateScheduleDetailRequest;
import com.haduc.go_travel_system.dto.request.CreateTourScheduleRequest;
import com.haduc.go_travel_system.dto.response.ScheduleDetailResponse;

public interface ScheduleDetailService {
    ScheduleDetailResponse createScheduleDetail(CreateScheduleDetailRequest request);
}
