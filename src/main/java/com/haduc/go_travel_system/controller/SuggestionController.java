package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.ImageSuggestionRequest;
import com.haduc.go_travel_system.service.ImageSuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/suggestion")
public class SuggestionController {
    private final ImageSuggestionService imageSuggestionService;

    @Value("${openai.model}")
    private String MODEL;

    @PostMapping("/image")
    public String suggestImage(@RequestParam String prompt, @RequestParam Integer n, @RequestParam String size) {
        ImageSuggestionRequest request = ImageSuggestionRequest.builder()
                .model(MODEL)
                .prompt(prompt)
                .n(n)
                .size(size)
                .build();
        return imageSuggestionService.suggestImage(request);
    }


}
