package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.dto.request.ImageSuggestionRequest;

public interface ImageSuggestionService {
    String suggestImage(ImageSuggestionRequest request);
}
