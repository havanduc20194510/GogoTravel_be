package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.CreateTourScheduleRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.TourScheduleResponse;
import com.haduc.go_travel_system.service.TourScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tour-schedule")
@CrossOrigin(origins = "*")
public class TourScheduleController {
    private final TourScheduleService tourScheduleService;

    @PostMapping("/create")
    public ApiResponse<TourScheduleResponse> createTourSchedule(@RequestBody CreateTourScheduleRequest request) {
        TourScheduleResponse tourScheduleResponse = tourScheduleService.createTourSchedule(request);
        return ApiResponse.<TourScheduleResponse>builder().data(tourScheduleResponse).message("create tour schedule successfully!").build();
    }
}
