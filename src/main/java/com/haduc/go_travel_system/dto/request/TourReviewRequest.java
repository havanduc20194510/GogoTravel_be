package com.haduc.go_travel_system.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourReviewRequest {
    private String tourId;
    private String userId;
    private String content;
    private int rating;
}
