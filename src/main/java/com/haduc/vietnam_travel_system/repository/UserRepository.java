package com.haduc.vietnam_travel_system.repository;

import com.haduc.vietnam_travel_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
