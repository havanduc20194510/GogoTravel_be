package com.haduc.go_travel_system.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AverageRatingResponse {
    private double averageRating;
    private int totalReview;
}
