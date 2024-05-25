package com.haduc.go_travel_system.repository;

import com.haduc.go_travel_system.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PlaceRepository extends JpaRepository<Place, Long>, PagingAndSortingRepository<Place,Long> {
    Page<Place> findAll(Pageable pageable);
    Page<Place> findAll(Specification<Place> spec, Pageable pageable);
    List<Place> findTop5ByOrderByTotalViewDesc();
}
