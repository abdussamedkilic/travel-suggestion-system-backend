package com.example.traveladvisorbackend.service;

import com.example.traveladvisorbackend.dto.PlaceDto;
import com.example.traveladvisorbackend.dto.PlaceWithTagDto;

import java.util.List;

public interface PlaceService {
    void addPlace(PlaceDto placeDto);

    void addPlaceMulti(List<PlaceDto> placeDtoList);

    void updatePlace(PlaceDto placeDto);

    List<PlaceDto> getAllPlaces();

    PlaceDto getPlaceById(String placeId);

    PlaceDto deletePlace(String placeId);

    PlaceDto getByName(String name);

    PlaceWithTagDto getPlacesByTag();

    List<PlaceDto> getPlaceByCityName(String name);

    List<PlaceDto> getSimilarPlaceByCityName(String cityName,String placeName);
}
