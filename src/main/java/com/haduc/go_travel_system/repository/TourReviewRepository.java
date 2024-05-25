package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.TourReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourReviewRepository extends JpaRepository<TourReview, Long> {
    List<TourReview> findByTourTourId(String tourId);

}
