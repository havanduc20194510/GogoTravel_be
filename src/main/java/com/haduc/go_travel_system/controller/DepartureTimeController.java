package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.CreateDepartureTimeRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.DepartureTimeResponse;
import com.haduc.go_travel_system.service.DepartureTimeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/departure-time")
@CrossOrigin(origins = "*")
public class DepartureTimeController {
    private DepartureTimeService departureTimeService;

    @PostMapping("/create")
    public ApiResponse<DepartureTimeResponse> createDepartureTime(@RequestBody CreateDepartureTimeRequest request) {
        DepartureTimeResponse departureTimeResponse = departureTimeService.createDepartureTime(request);
        return ApiResponse.<DepartureTimeResponse>builder()
                .message("Departure time created successfully")
                .data(departureTimeResponse)
                .build();
    }
}
