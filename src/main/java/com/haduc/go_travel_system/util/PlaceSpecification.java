package com.haduc.go_travel_system.util;

import com.haduc.go_travel_system.entity.Place;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

public class PlaceSpecification {
    public static Specification<Place> searchPlaces(String name, String address, String activities) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (address != null && !address.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + address.toLowerCase() + "%"));
            }

            if (activities != null && !activities.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("activities")), "%" + activities.toLowerCase() + "%"));
            }

            if (predicates.isEmpty()) {
                return criteriaBuilder.and();
            }

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
