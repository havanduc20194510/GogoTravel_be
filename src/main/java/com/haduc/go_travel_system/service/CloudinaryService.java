package com.haduc.go_travel_system.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface CloudinaryService {
    Map uploadImage(MultipartFile file) throws IOException;
    Map deleteImage(String publicId);
}
