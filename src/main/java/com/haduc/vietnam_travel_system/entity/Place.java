package com.haduc.vietnam_travel_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String address;
    private String image;
    private String type;
    private String location;
    private String price;
    private String timeOpen;
    private String timeClose;
    private String phone;
    private String status;
    private String note;
    private String rating;
    private String tag;
    private String createAt;
    private String updateAt;
}
