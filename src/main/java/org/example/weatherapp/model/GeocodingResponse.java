package org.example.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeocodingResponse(
        List<CityLocation> results
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CityLocation(
            double latitude,
            double longitude
    ) {}
}