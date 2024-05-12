package com.haduc.go_travel_system.util;

import com.haduc.go_travel_system.entity.Tour;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TourSpecification {
    public static Specification<Tour> searchTours(String destination, String departureLocation, LocalDate startDate, Long numberOfDay, Double filterPriceMin, Double filterPriceMax, String filterType) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (destination != null && !destination.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + destination.toLowerCase() + "%"));
            }

            if (departureLocation != null && !departureLocation.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("departureLocation")), "%" + departureLocation.toLowerCase() + "%"));
            }

            if (startDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("departureTimes").get("startDate"), startDate));
            }

            if (numberOfDay != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("numberOfDays"), numberOfDay));
            }

            if (filterPriceMin != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("babyPrice"), filterPriceMin));
            }

            if (filterPriceMax != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("adultPrice"), filterPriceMax));
            }

            if (filterType != null && !filterType.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("tourTypeName")), "%" + filterType.toLowerCase() + "%"));
            }

            if (predicates.isEmpty()) {
                return criteriaBuilder.and();
            }

            Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            query.distinct(true);
            query.where(finalPredicate);
            return query.getRestriction();
        };
    }
}
