package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.request.TourReviewRequest;
import com.haduc.go_travel_system.dto.response.AverageRatingResponse;
import com.haduc.go_travel_system.dto.response.TourReviewResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TourReviewService {
    TourReviewResponse createTourReview(TourReviewRequest request);
    TourReviewResponse updateTourReview(TourReviewRequest request, Long reviewId);
    String deleteTourReview(Long id);
    List<TourReviewResponse> getTourReviewsByTourId(String tourId);
    AverageRatingResponse getAverageRatingByTourId(String tourId);
    List<TourReviewResponse> getTop3TourReviews();
}
