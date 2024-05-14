package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.entity.TourType;
import com.haduc.go_travel_system.service.TourTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tour-type")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TourTypeController {
    private final TourTypeService tourTypeService;

    @PostMapping("/create")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<?> createTourType(@RequestBody TourType tourType) {
        return ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(tourTypeService.createTourType(tourType)).build();
    }

    @PutMapping("/update")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<?> updateTourType(@RequestBody TourType tourType) {
        return ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(tourTypeService.updateTourType(tourType.getId(), tourType)).build();
    }

    @DeleteMapping("/delete")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<?> deleteTourType(@RequestBody Long id) {
        tourTypeService.deleteTourType(id);
        return ApiResponse.builder()
                .code(200)
                .message("Success")
                .build();
    }

    @GetMapping("/get-all")
    public ApiResponse<?> getAllTourType() {
        return ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(tourTypeService.getAllTourType()).build();
    }

    @GetMapping("/get-by-id")
    public ApiResponse<?> getTourTypeById(@RequestBody Long id) {
        return ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(tourTypeService.getTourTypeById(id)).build();
    }

    @GetMapping("/get-by-name")
    public ApiResponse<?> getTourTypeByName(@RequestBody String name) {
        return ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(tourTypeService.getTourTypeByName(name)).build();
    }

}
