package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.CreateTourRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.CreateTourResponse;
import com.haduc.go_travel_system.dto.response.TourResponse;
import com.haduc.go_travel_system.service.TourService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Tag(
        name = "CRUD REST APIs for Tour Resource",
        description = "CRUD REST APIs - Create Tour, Update Tour, Get Tour, Get list Tours, Delete Tour"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/tour")
@CrossOrigin(origins = "*")
public class TourController {
    private final TourService tourService;
    @PostMapping("/create")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<CreateTourResponse> createTour(@RequestBody CreateTourRequest request) {
        CreateTourResponse createTourResponse = tourService.createTour(request);
        return ApiResponse.<CreateTourResponse>builder().data(createTourResponse).message("create tour successfully!").build();
    }

    @PutMapping("/increase-view/{tourId}")
    public ApiResponse<TourResponse> increaseView(@PathVariable String tourId) {
        TourResponse tourResponse = tourService.increaseView(tourId);
        return ApiResponse.<TourResponse>builder().data(tourResponse).message("increase view successfully!").build();
    }
    @PutMapping("/update/{tourId}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<TourResponse> updateTour(@RequestBody CreateTourRequest request, @PathVariable String tourId) {
        TourResponse tourResponse = tourService.updateTour(request, tourId);
        return ApiResponse.<TourResponse>builder().data(tourResponse).message("update tour successfully!").build();
    }

    @DeleteMapping("/delete/{tourId}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<String> deleteTour(@PathVariable String tourId) {
        String message = tourService.deleteTour(tourId);
        return ApiResponse.<String>builder().data(message).message("delete tour successfully!").build();
    }

    @PostMapping("/upload-image/{tourId}")
    @SecurityRequirement(name = "Bearer Authentication")
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

    @GetMapping("/list/pagination")
    public ApiResponse<List<TourResponse>> getTourWithPagination(@RequestParam(defaultValue = "1") int offset, @RequestParam(defaultValue = "5") int pageSize) {
        List<TourResponse> tours = tourService.findToursWithPagination(offset, pageSize).getContent();
        return ApiResponse.<List<TourResponse>>builder().data(tours).message("get tour with pagination successfully!").build();
    }

    @GetMapping("/list/pagination/sort")
    public ApiResponse<List<TourResponse>> getTourWithPaginationAndSort(@RequestParam(defaultValue = "1") int offset, @RequestParam(defaultValue = "5") int pageSize, @RequestParam String sortField) {
        List<TourResponse> tours = tourService.findToursWithPaginationAndSort(offset, pageSize, sortField).getContent();
        return ApiResponse.<List<TourResponse>>builder().data(tours).message("get tour with pagination and sort successfully!").build();
    }


    @GetMapping("/search/pagination/sort/filter")
    public ApiResponse<Page<TourResponse>> searchTourWithPaginationAndSortAndFilter(@RequestParam(required = false) String destination, @RequestParam(required = false) String departureLocation, @RequestParam(required = false) LocalDate startDate, @RequestParam(required = false) Long numberOfDay, @RequestParam(required = false) String filterType, @RequestParam(defaultValue = "0") Double filterPriceMin, @RequestParam(defaultValue = "1000000000") Double filterPriceMax, @RequestParam(defaultValue = "name") String sortField, @RequestParam(defaultValue = "1") int offset, @RequestParam(defaultValue = "5") int pageSize) {
        Page<TourResponse> tours = tourService.searchTourWithPaginationAndSortAndFilter(destination, departureLocation, startDate, numberOfDay, filterType, filterPriceMin, filterPriceMax, sortField, offset, pageSize);
        return ApiResponse.<Page<TourResponse>>builder().data(tours).message("search tour with pagination and sort and filter successfully!").build();
    }

    @GetMapping("/top-tour")
    public ApiResponse<List<TourResponse>> topTourRecommend() {
        List<TourResponse> tours = tourService.topTourRecommend();
        return ApiResponse.<List<TourResponse>>builder().data(tours).message("get top tour recommend successfully!").build();
    }
}
