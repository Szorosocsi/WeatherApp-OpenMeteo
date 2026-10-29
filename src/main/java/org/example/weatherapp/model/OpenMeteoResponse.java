package org.example.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenMeteoResponse(
        @JsonProperty("current_weather") CurrentWeather currentWeather,
        @JsonProperty("hourly") HourlyData hourly
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CurrentWeather(
            double temperature,
            double windspeed
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record HourlyData(
            @JsonProperty("temperature_2m") List<Double> temperature2m,
            @JsonProperty("time") List<String> time
    ) {}

}