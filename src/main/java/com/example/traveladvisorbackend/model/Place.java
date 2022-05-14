package com.example.traveladvisorbackend.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Slf4j
@Data
@Document("Place")
public class Place {
    @Id
    private String id;

    @Field(name = "name")
    @Indexed(unique = true)
    private String name;

    @Field(name = "location")
    private String location;

    @Field(name = "type")
    private PlaceType type;

    @Field(name = "image")
    private String image;

    @Field(name = "detail")
    private String detail;

    @Field(name = "rating")
    private double rating;

    @Field(name = "comments")
    private List<String> comments;


}
