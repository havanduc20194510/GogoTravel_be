package com.haduc.go_travel_system.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageSuggestionRequest {
    private String model;

    private String prompt;

    private Integer n;

    private String size;
}
