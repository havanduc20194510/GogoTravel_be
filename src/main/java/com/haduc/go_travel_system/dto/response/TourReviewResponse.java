package com.haduc.go_travel_system.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourReviewResponse {
    private String tourId;
    private String userId;
    private String userName;
    private String content;
    private int rating;
}
