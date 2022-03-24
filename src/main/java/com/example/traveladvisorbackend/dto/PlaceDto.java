package com.example.traveladvisorbackend.dto;

import com.example.traveladvisorbackend.model.PlaceType;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class PlaceDto {

    @NotEmpty
    @Valid
    private String name;

    @NotEmpty
    @Valid
    private String location;

    @NotEmpty
    @Valid
    private PlaceType type;

    private String image;

    private String detail;

    private double rating;

    private List<String> comments;

}
