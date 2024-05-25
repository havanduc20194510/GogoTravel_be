package com.haduc.go_travel_system.mapper;

import com.haduc.go_travel_system.dto.request.TourReviewRequest;
import com.haduc.go_travel_system.dto.response.TourReviewResponse;
import com.haduc.go_travel_system.entity.Tour;
import com.haduc.go_travel_system.entity.TourReview;
import com.haduc.go_travel_system.entity.User;
import com.haduc.go_travel_system.enums.ErrorCode;
import com.haduc.go_travel_system.exception.AppException;
import com.haduc.go_travel_system.repository.TourRepository;
import com.haduc.go_travel_system.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Builder
public class TourReviewMapper {
    private final UserRepository userRepository;
    private final TourRepository tourRepository;

    public TourReviewResponse toTourReviewResponse(TourReview tourReview) {
        return TourReviewResponse.builder()
                .tourId(tourReview.getTour().getTourId())
                .userId(tourReview.getUser().getId())
                .userName(tourReview.getUser().getUsername())
                .content(tourReview.getContent())
                .rating(tourReview.getRating())
                .build();
    }

    public TourReview toTourReview(TourReviewRequest tourReviewRequest) {
        User user = userRepository.findById(tourReviewRequest.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Tour tour = tourRepository.findById(tourReviewRequest.getTourId())
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_NOT_FOUND));
        return TourReview.builder()
                .user(user)
                .tour(tour)
                .content(tourReviewRequest.getContent())
                .rating(tourReviewRequest.getRating())
                .build();
    }
}
