package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.CreateTourScheduleRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.TourResponse;
import com.haduc.go_travel_system.dto.response.TourScheduleResponse;
import com.haduc.go_travel_system.repository.TourScheduleRepository;
import com.haduc.go_travel_system.service.TourScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/tour-schedule")
public class TourScheduleController {
    private TourScheduleService tourScheduleService;

    @PostMapping("/create")
    public ApiResponse<TourScheduleResponse> createTourSchedule(@RequestBody CreateTourScheduleRequest request) {
        TourScheduleResponse tourScheduleResponse = tourScheduleService.createTourSchedule(request);
        return ApiResponse.<TourScheduleResponse>builder().data(tourScheduleResponse).message("create tour schedule successfully!").build();
    }
}
