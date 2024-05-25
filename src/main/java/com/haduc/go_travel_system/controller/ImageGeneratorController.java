package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.dto.request.ImageGenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImageClient;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suggestion")
@RequiredArgsConstructor
public class ImageGeneratorController {
    private final ImageClient imageClient;

    @GetMapping("/image")
    public String getImage(@RequestParam String request){
        ImagePrompt prompt = new ImagePrompt(request);
        ImageResponse response = imageClient.call(prompt);
        return response.getResult().getOutput().getUrl();
    }
}
