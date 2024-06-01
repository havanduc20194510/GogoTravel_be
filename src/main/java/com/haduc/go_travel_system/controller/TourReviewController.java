package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.TourReviewRequest;
import com.haduc.go_travel_system.dto.response.ApiResponse;
import com.haduc.go_travel_system.dto.response.AverageRatingResponse;
import com.haduc.go_travel_system.dto.response.TourReviewResponse;
import com.haduc.go_travel_system.service.TourReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tour-reviews")
@CrossOrigin(origins = "*")
public class TourReviewController {
    private final TourReviewService tourReviewService;

    @PostMapping("/create")
    public ApiResponse<TourReviewResponse> createTourReview(@RequestBody TourReviewRequest request) {
        TourReviewResponse tourReviewResponse = tourReviewService.createTourReview(request);
        return ApiResponse.<TourReviewResponse>builder().data(tourReviewResponse).message("create tour review successfully!").build();
    }

    @PutMapping("/update/{reviewId}")
    public ApiResponse<TourReviewResponse> updateTourReview(@RequestBody TourReviewRequest request, @PathVariable Long reviewId) {
        TourReviewResponse tourReviewResponse = tourReviewService.updateTourReview(request, reviewId);
        return ApiResponse.<TourReviewResponse>builder().data(tourReviewResponse).message("update tour review successfully!").build();
    }

    @DeleteMapping("/delete/{reviewId}")
    public ApiResponse<String> deleteTourReview(@PathVariable Long reviewId) {
        String message = tourReviewService.deleteTourReview(reviewId);
        return ApiResponse.<String>builder().data(message).message("delete tour review successfully!").build();
    }

    @GetMapping("/list/{tourId}")
    public ApiResponse<List<TourReviewResponse>> getTourReviewsByTourId(@PathVariable String tourId) {
        List<TourReviewResponse> tourReviewResponses = tourReviewService.getTourReviewsByTourId(tourId);
        return ApiResponse.<List<TourReviewResponse>>builder().data(tourReviewResponses).message("get tour reviews by tourId successfully!").build();
    }

    @GetMapping("/average-rating/{tourId}")
    public ApiResponse<AverageRatingResponse> getAverageRatingByTourId(@PathVariable String tourId) {
        AverageRatingResponse averageRatingResponse = tourReviewService.getAverageRatingByTourId(tourId);
        return ApiResponse.<AverageRatingResponse>builder().data(averageRatingResponse).message("get average rating by tourId successfully!").build();
    }

    @GetMapping("/top-3")
    public ApiResponse<List<TourReviewResponse>> getTop3TourReviews() {
        List<TourReviewResponse> tourReviewResponses = tourReviewService.getTop3TourReviews();
        return ApiResponse.<List<TourReviewResponse>>builder().data(tourReviewResponses).message("get top 3 tour reviews successfully!").build();
    }
}
