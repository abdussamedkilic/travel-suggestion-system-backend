package com.example.traveladvisorbackend.service.impl;

import com.example.traveladvisorbackend.dto.PlaceDto;
import com.example.traveladvisorbackend.dto.PlaceWithTagDto;
import com.example.traveladvisorbackend.dto.SuggestData;
import com.example.traveladvisorbackend.dto.mapper.BaseMapper;
import com.example.traveladvisorbackend.model.Place;
import com.example.traveladvisorbackend.model.PlaceType;
import com.example.traveladvisorbackend.repository.PlaceRepository;
import com.example.traveladvisorbackend.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final RestTemplate restTemplate;

    @Value("${app.suggest_url}")
    private String suggestUrl;

    @Override
    public void addPlace(PlaceDto placeDto) {
        Place place = BaseMapper.map(placeDto, Place.class);
        placeRepository.insert(place);
    }

    @Override
    public void addPlaceMulti(List<PlaceDto> placeDtoList) {
        List<Place> places = BaseMapper.mapAll(placeDtoList, Place.class);
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
        Optional<Place> place = Optional.of(placeRepository.findById(placeId).orElseThrow());
        return BaseMapper.map(place.get(), PlaceDto.class);
    }


    @Override
    public PlaceDto deletePlace(String placeId) {
        PlaceDto placeDto = BaseMapper.map(placeRepository.findById(placeId), PlaceDto.class);
        placeRepository.deleteById(placeId);
        return placeDto;
    }

    @Override
    public PlaceDto getByName(String name) {
        Place place = placeRepository.findByName(name);
        return BaseMapper.map(place, PlaceDto.class);
    }

    @Override
    public PlaceWithTagDto getPlacesByTag() {
        List<PlaceDto> doingPlaces = BaseMapper.mapAll(placeRepository.findPlaceByType(PlaceType.DO), PlaceDto.class);
        List<PlaceDto> eatingPlaces = BaseMapper.mapAll(placeRepository.findPlaceByType(PlaceType.EAT), PlaceDto.class);
        List<PlaceDto> stayingPlaces = BaseMapper.mapAll(placeRepository.findPlaceByType(PlaceType.STAY), PlaceDto.class);

        return PlaceWithTagDto.builder()
                .doingPlaces(doingPlaces).stayingPlaces(stayingPlaces).eatingPlaces(eatingPlaces).build();

    }

    @Override
    public List<PlaceDto> getPlaceByCityName(String name) {
        return BaseMapper.mapAll(placeRepository.findPlaceByLocation(name), PlaceDto.class);
    }

    @Override
    public List<PlaceDto> getSimilarPlaceByCityName(String cityName, String placeName) {
        SuggestData suggestData = getPlaceList(cityName, placeName);
        List<Place> places = suggestData.getTop_ten_names().stream().map(placeRepository::findByName)
                .collect(Collectors.toList());
        return BaseMapper.mapAll(places, PlaceDto.class);
    }

    private SuggestData getPlaceList(String cityName, String placeName) {
        final String url = suggestUrl + cityName + "/" + placeName;
        ResponseEntity<SuggestData> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        if (response.hasBody()) {
            return Objects.requireNonNull(response.getBody());
        } else {
            return (SuggestData) Collections.emptyList();
        }
    }
}
