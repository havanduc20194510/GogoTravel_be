package com.haduc.go_travel_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceImage> images;
    private String location;
    private String timeOpen;
    private String timeClose;
    private String note;
    private String activities;
    private Long totalView;
}
