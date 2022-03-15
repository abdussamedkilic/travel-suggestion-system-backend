package com.example.traveladvisorbackend.service;

import com.example.traveladvisorbackend.dto.PlaceDto;

import java.util.List;

public interface PlaceService {
    void addPlace(PlaceDto placeDto);

    void addPlaceMulti(List<PlaceDto> placeDtoList);

    void updatePlace(PlaceDto placeDto);

    List<PlaceDto> getAllPlaces();

    PlaceDto getPlaceById(String placeId);

    PlaceDto deletePlace(String placeId);
}
