package com.haduc.go_travel_system.service;

import com.haduc.go_travel_system.entity.TourType;

import java.util.List;

public interface TourTypeService {
    List<TourType> getAllTourType();
    TourType getTourTypeById(Long id);
    TourType getTourTypeByName(String name);
    TourType createTourType(TourType tourType);
    TourType updateTourType(Long id, TourType tourType);
    void deleteTourType(Long id);
}
