package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.dto.request.CreatePlaceRequest;
import com.haduc.go_travel_system.dto.response.PlaceResponse;
import com.haduc.go_travel_system.entity.Place;
import com.haduc.go_travel_system.entity.PlaceImage;
import com.haduc.go_travel_system.enums.ErrorCode;
import com.haduc.go_travel_system.exception.AppException;
import com.haduc.go_travel_system.mapper.PlaceMapper;
import com.haduc.go_travel_system.repository.PlaceImageRepository;
import com.haduc.go_travel_system.repository.PlaceRepository;
import com.haduc.go_travel_system.service.CloudinaryService;
import com.haduc.go_travel_system.service.PlaceService;
import com.haduc.go_travel_system.util.PlaceSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceMapper placeMapper;
    private final PlaceRepository placeRepository;
    private final CloudinaryService cloudinaryService;
    private final PlaceImageRepository placeImageRepository;


    @Override
    public PlaceResponse createPlace(CreatePlaceRequest request) {
        Place place = placeMapper.toPlace(request);
        place.setTotalView(0L);
        Place placeSaved = placeRepository.save(place);
        return placeMapper.toDto(placeSaved);
    }

    @Override
    public PlaceResponse updatePlace(Long placeId, CreatePlaceRequest request) {
        Place place = placeRepository.findById(placeId).orElseThrow(() -> new AppException(ErrorCode.PLACE_NOT_FOUND));
        place.setName(request.getName());
        place.setDescription(request.getDescription());
        place.setAddress(request.getAddress());
        place.setLocation(request.getLocation());
        place.setTimeOpen(request.getTimeOpen());
        place.setTimeClose(request.getTimeClose());
        place.setNote(request.getNote());
        place.setActivities(request.getActivities());
        Place placeSaved = placeRepository.save(place);
        return placeMapper.toDto(placeSaved);
    }

    @Override
    public PlaceResponse uploadImage(Long placeId, MultipartFile[] files) throws IOException {
        Place place = placeRepository.findById(placeId).orElseThrow(() -> new AppException(ErrorCode.PLACE_NOT_FOUND));
        for (MultipartFile file : files) {
            Map uploadResult = cloudinaryService.uploadImage(file);
            PlaceImage placeImage = new PlaceImage();
            placeImage.setUrl(uploadResult.get("url").toString());
            placeImage.setPlace(place);
            placeImageRepository.save(placeImage);
        }
        List<PlaceImage> placeImages = placeImageRepository.findByPlaceId(place.getId());
        place.setImages(placeImages);
        return placeMapper.toDto(place);
    }

    @Override
    public String deletePlace(Long placeId) {
        Place place = placeRepository.findById(placeId).orElseThrow(() -> new AppException(ErrorCode.PLACE_NOT_FOUND));
        placeRepository.delete(place);
        return "Place deleted successfully";
    }

    @Override
    public PlaceResponse getPlace(Long placeId) {
        Place place = placeRepository.findById(placeId).orElseThrow(() -> new AppException(ErrorCode.PLACE_NOT_FOUND));
        return placeMapper.toDto(place);
    }


    @Override
    public List<PlaceResponse> getAllPlaces() {
        List<Place> places = placeRepository.findAll();
        return places.stream().map(placeMapper::toDto).toList();
    }

    @Override
    public Page<PlaceResponse> getAllPlacesAndPagination(int offset, int pageSize) {
        Page<Place> places = placeRepository.findAll(PageRequest.of(offset -1 , pageSize));
        return places.map(placeMapper::toDto);
    }

    @Override
    public Page<PlaceResponse> searchPlace(String name, String address, String activities, int offset, int pageSize) {
        Pageable pageable = PageRequest.of(offset - 1, pageSize);
        Specification<Place> spec = PlaceSpecification.searchPlaces(name, address, activities);
        Page<Place> places = placeRepository.findAll(spec, pageable);
        return places.map(placeMapper::toDto);
    }

    @Override
    public PlaceResponse increaseView(Long placeId) {
        Place place = placeRepository.findById(placeId).orElseThrow(() -> new AppException(ErrorCode.PLACE_NOT_FOUND));
        place.setTotalView(place.getTotalView() + 1);
        Place placeSaved = placeRepository.save(place);
        return placeMapper.toDto(placeSaved);
    }

    @Override
    public List<PlaceResponse> topPlaceRecommend() {
        List<Place> places = placeRepository.findTop5ByOrderByTotalViewDesc();
        return places.stream().map(placeMapper::toDto).toList();
    }
}
