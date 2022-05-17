package com.example.traveladvisorbackend.controller;

import com.example.traveladvisorbackend.dto.PlaceDto;
import com.example.traveladvisorbackend.dto.PlaceWithTagDto;
import com.example.traveladvisorbackend.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/place")
@RequiredArgsConstructor
@Slf4j
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping
    public ResponseEntity<PlaceDto> addPlace(@RequestBody PlaceDto placeDto) {
        placeService.addPlace(placeDto);
        return new ResponseEntity<>(placeDto, HttpStatus.OK);
    }

    @PostMapping("/addMultiply")
    public ResponseEntity<List<PlaceDto>> addPlaceMultiply(@RequestBody List<PlaceDto> placeDtoList) {
        placeService.addPlaceMulti(placeDtoList);
        return new ResponseEntity<>(placeDtoList, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PlaceDto> updatePlace(@RequestBody PlaceDto placeDto) {
        placeService.updatePlace(placeDto);
        return new ResponseEntity<>(placeDto, HttpStatus.OK);
    }

    @GetMapping("/places/allPlaces/")
    public ResponseEntity<List<PlaceDto>> getAllPlaces() {
        List<PlaceDto> placeDtoList = placeService.getAllPlaces();
        return new ResponseEntity<>(placeDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceDto> getPlaceById(@PathVariable String id) {
        PlaceDto place = placeService.getPlaceById(id);
        return new ResponseEntity<>(place, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlaceDto> deletePlace(@PathVariable String id) {
        PlaceDto place = placeService.deletePlace(id);
        return new ResponseEntity<>(place, HttpStatus.OK);
    }

    @GetMapping("/name/{placeName}")
    public ResponseEntity<PlaceDto> getPlaceByPlaceName(@PathVariable String placeName) {
        PlaceDto place = placeService.getByName(placeName);
        return new ResponseEntity<>(place, HttpStatus.OK);
    }

    @GetMapping("/places/tag")
    public ResponseEntity<PlaceWithTagDto> getPlaceListByTag() {
        PlaceWithTagDto places = placeService.getPlacesByTag();
        return new ResponseEntity<>(places, HttpStatus.OK);
    }

    @GetMapping("/places/city/{city}")
    public ResponseEntity<List<PlaceDto>> getPlaceListByCity(@PathVariable String city) {
        List<PlaceDto> places = placeService.getPlaceByCityName(city);
        return new ResponseEntity<>(places, HttpStatus.OK);
    }

}
