package com.haduc.go_travel_system.dto.response;

import lombok.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageSuggestionResponse {
    private Date created;
    private List<HashMap<String, String>> data;
}
