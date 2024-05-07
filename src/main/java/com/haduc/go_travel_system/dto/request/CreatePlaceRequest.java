package com.haduc.go_travel_system.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlaceRequest {
    private String name;
    private String description;
    private String address;
    private String location;
    private String timeOpen;
    private String timeClose;
    private String note;
    private String activities;
}
