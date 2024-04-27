package com.haduc.go_travel_system.dto.request;

import com.haduc.go_travel_system.entity.Activity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlaceRequest {
    private Long id;
    private String name;
    private String description;
    private String address;
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
    private Long activityId;
}
