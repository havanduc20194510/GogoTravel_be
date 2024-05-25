package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.CreateDepartureTimeRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.DepartureTimeResponse;
import com.haduc.go_travel_system.entity.DepartureTime;
import com.haduc.go_travel_system.repository.DepartureTimeRepository;
import com.haduc.go_travel_system.service.DepartureTimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departure-time")
@CrossOrigin(origins = "*")
public class DepartureTimeController {
    private final DepartureTimeService departureTimeService;
    private final DepartureTimeRepository departureTimeRepository;

    @PostMapping("/create")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<DepartureTimeResponse> createDepartureTime(@RequestBody CreateDepartureTimeRequest request) {
        DepartureTimeResponse departureTimeResponse = departureTimeService.createDepartureTime(request);
        return ApiResponse.<DepartureTimeResponse>builder()
                .message("Departure time created successfully")
                .data(departureTimeResponse)
                .build();
    }

    @GetMapping("/get/{departureTimeId}")
    public ApiResponse<DepartureTimeResponse> getDepartureTime(@PathVariable Long departureTimeId) {
        DepartureTimeResponse departureTimeResponse = departureTimeService.getDepartureTime(departureTimeId);
        return ApiResponse.<DepartureTimeResponse>builder()
                .message("Departure time retrieved successfully")
                .data(departureTimeResponse)
                .build();
    }

    @PutMapping("/update/{departureTimeId}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<DepartureTimeResponse> updateDepartureTime(@RequestBody CreateDepartureTimeRequest request, @PathVariable Long departureTimeId) {
        DepartureTimeResponse departureTimeResponse = departureTimeService.updateDepartureTime(request, departureTimeId);
        return ApiResponse.<DepartureTimeResponse>builder()
                .message("Departure time updated successfully")
                .data(departureTimeResponse)
                .build();
    }

    @DeleteMapping("/delete/{departureTimeId}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<String> deleteDepartureTime(@PathVariable Long departureTimeId) {
        String message = departureTimeService.deleteDepartureTime(departureTimeId);
        return ApiResponse.<String>builder()
                .message("Departure time deleted successfully")
                .data(message)
                .build();
    }

    @GetMapping("/get-by-tour-id/{tourId}")
    public ApiResponse<List<DepartureTimeResponse>> getDepartureTimeByTourId(@PathVariable String tourId) {
        List<DepartureTimeResponse> departureTimeResponses = departureTimeService.getDepartureTimeByTourId(tourId);
        return ApiResponse.<List<DepartureTimeResponse>>builder()
                .message("Departure time retrieved successfully")
                .data(departureTimeResponses)
                .build();
    }

    @Operation(summary = "Update available test. Dont use this in production.")
    @PutMapping("/update/available/{departureTimeId}")
    public String updateAvailable(@PathVariable Long departureTimeId, @RequestParam boolean available) {
        DepartureTime departureTime = departureTimeRepository.findById(departureTimeId).orElseThrow(() -> new RuntimeException("Departure time not found"));
        departureTime.setAvailable(available);
        departureTimeRepository.save(departureTime);
        return "Update available successfully";
    }

}
