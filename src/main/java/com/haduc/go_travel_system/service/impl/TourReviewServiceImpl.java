package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.dto.request.TourReviewRequest;
import com.haduc.go_travel_system.dto.response.AverageRatingResponse;
import com.haduc.go_travel_system.dto.response.TourReviewResponse;
import com.haduc.go_travel_system.entity.TourReview;
import com.haduc.go_travel_system.enums.BookingStatus;
import com.haduc.go_travel_system.enums.ErrorCode;
import com.haduc.go_travel_system.exception.AppException;
import com.haduc.go_travel_system.mapper.TourReviewMapper;
import com.haduc.go_travel_system.repository.BookingTourRepository;
import com.haduc.go_travel_system.repository.TourReviewRepository;
import com.haduc.go_travel_system.service.TourReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TourReviewServiceImpl implements TourReviewService {
    private final TourReviewMapper tourReviewMapper;
    private final TourReviewRepository tourReviewRepository;
    private final BookingTourRepository bookingTourRepository;

    @Override
    public TourReviewResponse createTourReview(TourReviewRequest request) {
        if (bookingTourRepository.existsByTourTourIdAndUserIdAndStatus(request.getTourId(), request.getUserId(), BookingStatus.CONFIRMED)) {
            throw new AppException(ErrorCode.USER_NOT_BOOKED_TOUR);
        }
        TourReview tourReview = tourReviewMapper.toTourReview(request);
        return tourReviewMapper.toTourReviewResponse(tourReviewRepository.save(tourReview));
    }

    @Override
    public TourReviewResponse updateTourReview(TourReviewRequest request, Long reviewId) {
        if (bookingTourRepository.existsByTourTourIdAndUserIdAndStatus(request.getTourId(), request.getUserId(), BookingStatus.CONFIRMED)) {
            throw new AppException(ErrorCode.USER_NOT_BOOKED_TOUR);
        }
        TourReview tourReview = tourReviewRepository.findById(reviewId)
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_REVIEW_NOT_FOUND));
        tourReview.setContent(request.getContent());
        tourReview.setRating(request.getRating());
        return tourReviewMapper.toTourReviewResponse(tourReviewRepository.save(tourReview));
    }

    @Override
    public String deleteTourReview(Long reviewId) {
        tourReviewRepository.deleteById(reviewId);
        return "Delete success";
    }

    @Override
    public List<TourReviewResponse> getTourReviewsByTourId(String tourId) {
        List<TourReview> tourReviews = tourReviewRepository.findByTourTourId(tourId);
        return tourReviews.stream().map(tourReviewMapper::toTourReviewResponse).toList();
    }

    @Override
    public AverageRatingResponse getAverageRatingByTourId(String tourId) {
        List<TourReview> tourReviews = tourReviewRepository.findByTourTourId(tourId);
        double averageRating = tourReviews.stream().mapToDouble(TourReview::getRating).average().orElse(0);
        return AverageRatingResponse.builder()
                .averageRating(averageRating)
                .totalReview(tourReviews.size())
                .build();
    }

    @Override
    public List<TourReviewResponse> getTop3TourReviews() {
        List<TourReview> tourReviews = tourReviewRepository.findTop3ByOrderByRatingDesc();
        return tourReviews.stream().map(tourReviewMapper::toTourReviewResponse).toList();
    }
}
