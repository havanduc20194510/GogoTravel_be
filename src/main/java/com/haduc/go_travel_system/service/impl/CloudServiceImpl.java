package com.haduc.go_travel_system.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.haduc.go_travel_system.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudServiceImpl implements CloudinaryService{
    private Cloudinary cloudinary;
    @Override
    public Map uploadImage(MultipartFile file) throws IOException {
        BufferedImage bi = ImageIO.read(file.getInputStream());
        if (bi == null) {
            throw new IOException("Invalid file");
        }
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return uploadResult;
    }

    @Override
    public Map deleteImage(String publicId) {
        return null;
    }
}
