package com.example.traveladvisorbackend.repository;

import com.example.traveladvisorbackend.model.Place;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaceRepository extends MongoRepository<Place, String> {

}
