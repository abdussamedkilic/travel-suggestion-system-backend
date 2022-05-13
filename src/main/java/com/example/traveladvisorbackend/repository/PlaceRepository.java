package com.example.traveladvisorbackend.repository;

import com.example.traveladvisorbackend.model.Place;
import com.example.traveladvisorbackend.model.PlaceType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlaceRepository extends MongoRepository<Place, String> {
    Place findByName(String name);

    List<Place> findPlaceByType(PlaceType type);

    List<Place> findPlaceByLocation(String city);
}
