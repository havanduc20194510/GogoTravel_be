package com.haduc.go_travel_system.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
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
