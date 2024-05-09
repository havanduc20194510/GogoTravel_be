package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.dto.request.ImageSuggestionRequest;
import com.haduc.go_travel_system.dto.response.ImageSuggestionResponse;
import com.haduc.go_travel_system.service.ImageSuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ImageSuggestionImageImpl implements ImageSuggestionService {
    private final RestTemplate restTemplate;
    
    @Value("${openai.api.url}")
    private String OPENAIURL;
    @Override
    public String suggestImage(ImageSuggestionRequest request) {
        ImageSuggestionResponse imageSuggestionResponse = restTemplate.postForObject(OPENAIURL, request, ImageSuggestionResponse.class);
        return imageSuggestionResponse.getData().get(0).get("url").toString();
    }
}
