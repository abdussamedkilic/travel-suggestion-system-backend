package com.example.traveladvisorbackend.service.impl;

import com.example.traveladvisorbackend.dto.PlaceDto;
import com.example.traveladvisorbackend.dto.mapper.BaseMapper;
import com.example.traveladvisorbackend.model.Place;
import com.example.traveladvisorbackend.repository.PlaceRepository;
import com.example.traveladvisorbackend.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    @Override
    public void addPlace(PlaceDto placeDto) {
        Place place = BaseMapper.map(placeDto, Place.class);
        placeRepository.insert(place);
    }

    @Override
    public void addPlaceMulti(List<PlaceDto> placeDtoList) {
        List<Place> places = BaseMapper.mapAll(placeDtoList,Place.class);
        placeRepository.insert(places);
    }

    @Override
    public void updatePlace(PlaceDto placeDto) {
        // TODO: 15.03.2022 updating method must be developing
    }

    @Override
    public List<PlaceDto> getAllPlaces() {
        return BaseMapper.mapAll(placeRepository.findAll(), PlaceDto.class);
    }

    @Override
    public PlaceDto getPlaceById(String placeId) {
        Optional<Place> place = placeRepository.findById(placeId);
        return BaseMapper.map(place.get(), PlaceDto.class);
    }


    @Override
    public PlaceDto deletePlace(String placeId) {
        PlaceDto placeDto = BaseMapper.map(placeRepository.findById(placeId), PlaceDto.class);
        placeRepository.deleteById(placeId);
        return placeDto;
    }
}
