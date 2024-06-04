package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, String>, PagingAndSortingRepository<Tour, String> {
    Page<Tour> findAll(Specification<Tour> spec, Pageable pageable);
    List<Tour> findTop6ByOrderByTotalViewDesc();

}
