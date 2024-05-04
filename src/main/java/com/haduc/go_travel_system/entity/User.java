package com.haduc.go_travel_system.entity;

import com.haduc.go_travel_system.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private Set<String> roles;
}
