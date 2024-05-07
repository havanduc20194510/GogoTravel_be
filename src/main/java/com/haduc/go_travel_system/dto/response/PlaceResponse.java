package com.haduc.go_travel_system.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceResponse {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String image;
    private String location;
    private String timeOpen;
    private String timeClose;
    private String note;
    private String activities;
}
