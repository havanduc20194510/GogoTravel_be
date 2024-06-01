package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.CreateTourScheduleRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.TourScheduleResponse;
import com.haduc.go_travel_system.service.TourScheduleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tour-schedule")
@CrossOrigin(origins = "*")
public class TourScheduleController {
    private final TourScheduleService tourScheduleService;

    @PostMapping("/create")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<TourScheduleResponse> createTourSchedule(@RequestBody CreateTourScheduleRequest request) {
        TourScheduleResponse tourScheduleResponse = tourScheduleService.createTourSchedule(request);
        return ApiResponse.<TourScheduleResponse>builder().data(tourScheduleResponse).message("create tour schedule successfully!").build();
    }

    @GetMapping("/list")
    public ApiResponse<List<TourScheduleResponse>> getTourSchedule() {
        List<TourScheduleResponse> tourScheduleResponses = tourScheduleService.getTourSchedule();
        return ApiResponse.<List<TourScheduleResponse>>builder().data(tourScheduleResponses).message("get tour schedule successfully!").build();
    }
    @GetMapping("/list/{tourId}")
    public ApiResponse<List<TourScheduleResponse>> getTourSchedule(@PathVariable String tourId) {
        List<TourScheduleResponse> tourScheduleResponses = tourScheduleService.getTourSchedule(tourId);
        return ApiResponse.<List<TourScheduleResponse>>builder().data(tourScheduleResponses).message("get tour schedule by tourId successfully!").build();
    }
}
