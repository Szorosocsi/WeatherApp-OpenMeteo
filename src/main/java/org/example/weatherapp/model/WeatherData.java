package org.example.weatherapp.model;

import java.time.LocalDate;
import java.util.List;

public record WeatherData(
        String city,
        LocalDate date,
        double minTemp,
        double maxTemp,
        List<Double> hourlyTemps
) {}