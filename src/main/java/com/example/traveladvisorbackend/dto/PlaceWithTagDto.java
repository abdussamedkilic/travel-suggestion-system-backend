package com.example.traveladvisorbackend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlaceWithTagDto {
    List<PlaceDto> eatingPlaces;
    List<PlaceDto> stayingPlaces;
    List<PlaceDto> doingPlaces;
}
