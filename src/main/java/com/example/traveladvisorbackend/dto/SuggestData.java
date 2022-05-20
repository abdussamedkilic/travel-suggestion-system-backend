package com.example.traveladvisorbackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class SuggestData {
    private List<String> top_ten_names;
    private boolean success;
}
