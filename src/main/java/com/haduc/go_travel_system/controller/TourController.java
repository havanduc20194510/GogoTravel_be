package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.CreateTourRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.CreateTourResponse;
import com.haduc.go_travel_system.dto.response.TourResponse;
import com.haduc.go_travel_system.service.TourService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Tag(
        name = "CRUD REST APIs for Tour Resource",
        description = "CRUD REST APIs - Create Tour, Update Tour, Get Tour, Get list Tours, Delete Tour"
)
@RestController
@AllArgsConstructor
@RequestMapping("/tour")
@CrossOrigin(origins = "*")
public class TourController {
    private TourService tourService;
    @PostMapping("/create")
    public ApiResponse<CreateTourResponse> createTour(@RequestBody CreateTourRequest request) {
        CreateTourResponse createTourResponse = tourService.createTour(request);
        return ApiResponse.<CreateTourResponse>builder().data(createTourResponse).message("create tour successfully!").build();
    }

    @PutMapping("/update/{tourId}")
    public ApiResponse<TourResponse> updateTour(@RequestBody CreateTourRequest request, @PathVariable String tourId) {
        TourResponse tourResponse = tourService.updateTour(request, tourId);
        return ApiResponse.<TourResponse>builder().data(tourResponse).message("update tour successfully!").build();
    }

    @DeleteMapping("/delete/{tourId}")
    public ApiResponse<String> deleteTour(@PathVariable String tourId) {
        String message = tourService.deleteTour(tourId);
        return ApiResponse.<String>builder().data(message).message("delete tour successfully!").build();
    }

    @PostMapping("/upload-image/{tourId}")
    public ApiResponse<TourResponse> uploadImage(@RequestPart("images") MultipartFile[] images, @PathVariable String tourId) {
        TourResponse tourResponse = tourService.uploadImage(images, tourId);
        return ApiResponse.<TourResponse>builder().data(tourResponse).message("upload image successfully!").build();
    }

    @GetMapping("/list")
    public ApiResponse<List<TourResponse>> getListTour() {
        List<TourResponse> tours = tourService.getAllTours();
        return ApiResponse.<List<TourResponse>>builder().data(tours).message("get list tour successfully!").build();
    }

    @GetMapping("/{tourId}")
    public ApiResponse<TourResponse> getTour(@PathVariable String tourId) {
        TourResponse tour = tourService.getTour(tourId);
        return ApiResponse.<TourResponse>builder().data(tour).message("get tour successfully!").build();
    }

    @GetMapping("list/pagination")
    public ApiResponse<List<TourResponse>> getTourWithPagination(@RequestParam(defaultValue = "1") int offset, @RequestParam(defaultValue = "5") int pageSize) {
        List<TourResponse> tours = tourService.findToursWithPagination(offset, pageSize).getContent();
        return ApiResponse.<List<TourResponse>>builder().data(tours).message("get tour with pagination successfully!").build();
    }

    @GetMapping("list/pagination/sort")
    public ApiResponse<List<TourResponse>> getTourWithPaginationAndSort(@RequestParam(defaultValue = "1") int offset, @RequestParam(defaultValue = "5") int pageSize, @RequestParam String sortField) {
        List<TourResponse> tours = tourService.findToursWithPaginationAndSort(offset, pageSize, sortField).getContent();
        return ApiResponse.<List<TourResponse>>builder().data(tours).message("get tour with pagination and sort successfully!").build();
    }

    @GetMapping("search")
    public ApiResponse<List<TourResponse>> searchTour(@RequestParam String destination, @RequestParam String departureLocation, @RequestParam LocalDate startDate, @RequestParam Long numberOfDay) {
        List<TourResponse> tours = tourService.searchTour(destination, departureLocation, startDate, numberOfDay);
        return ApiResponse.<List<TourResponse>>builder().data(tours).message("search tour successfully!").build();
    }

    @GetMapping("search/pagination")
    public ApiResponse<List<TourResponse>> searchTourWithPagination(@RequestParam String destination, @RequestParam String departureLocation, @RequestParam LocalDate startDate, @RequestParam Long numberOfDay, @RequestParam(defaultValue = "1") int offset, @RequestParam(defaultValue = "5") int pageSize) {
        List<TourResponse> tours = tourService.searchTourWithPagination(destination, departureLocation, startDate, numberOfDay, offset, pageSize).getContent();
        return ApiResponse.<List<TourResponse>>builder().data(tours).message("search tour with pagination successfully!").build();
    }

}
