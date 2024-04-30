package com.haduc.go_travel_system.controller;

import com.haduc.go_travel_system.service.CloudinaryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Tag(
        name = "CRUD REST APIs for Image Resource used Cloudinary",
        description = "CRUD REST APIs - Upload Image, Delete Image")
@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class CloudinaryController {
    private final CloudinaryService cloudinaryService;
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestPart("file") MultipartFile file) throws IOException {
        Map result = cloudinaryService.uploadImage(file);
        return ResponseEntity.ok(result.get("url").toString());
    }
}
