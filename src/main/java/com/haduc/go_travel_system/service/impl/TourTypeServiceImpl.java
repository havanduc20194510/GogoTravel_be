package com.haduc.go_travel_system.service.impl;

import com.haduc.go_travel_system.entity.TourType;
import com.haduc.go_travel_system.repository.TourTypeRepository;
import com.haduc.go_travel_system.service.TourTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourTypeServiceImpl implements TourTypeService {
    private final TourTypeRepository tourTypeRepository;

    @Override
    public List<TourType> getAllTourType() {
        return tourTypeRepository.findAll();
    }

    @Override
    public TourType getTourTypeById(Long id) {
        return tourTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Tour type not found"));
    }

    @Override
    public TourType getTourTypeByName(String name) {
        return tourTypeRepository.findByName(name);
    }

    @Override
    public TourType createTourType(TourType tourType) {
        return tourTypeRepository.save(tourType);
    }

    @Override
    public TourType updateTourType(Long id, TourType tourType) {
        TourType type = tourTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Tour type not found"));
        type.setName(tourType.getName());
        return tourTypeRepository.save(type);
    }

    @Override
    public void deleteTourType(Long id) {
        tourTypeRepository.deleteById(id);
    }
}
