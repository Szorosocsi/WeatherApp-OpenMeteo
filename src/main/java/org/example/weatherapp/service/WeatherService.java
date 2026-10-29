package org.example.weatherapp.service;

import org.example.weatherapp.client.WeatherApiClient;
import org.example.weatherapp.model.GeocodingResponse;
import org.example.weatherapp.model.OpenMeteoResponse;
import org.example.weatherapp.model.WeatherData;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class WeatherService {

    private final WeatherApiClient weatherApiClient;

    public WeatherService(WeatherApiClient weatherApiClient) {
        this.weatherApiClient = weatherApiClient;
    }

    public WeatherData getWeatherByCity(String city) {
        GeocodingResponse geo = weatherApiClient.fetchCoordinates(city);

        if (geo == null || geo.results() == null || geo.results().isEmpty()) {
            throw new IllegalArgumentException("City not found: " + city);
        }

        double lat = geo.results().get(0).latitude();
        double lon = geo.results().get(0).longitude();

        OpenMeteoResponse weather = weatherApiClient.fetchWeather(lat, lon);

        if (weather == null || weather.currentWeather() == null) {
            throw new IllegalStateException("Failed to parse weather payload from external API");
        }

        double currentTemp = weather.currentWeather().temperature();

        var hourly = weather.hourly();
        System.out.println("Current temperature: " + currentTemp);

        List<Double> rawTemps = (hourly != null) ? hourly.temperature2m() : null;
        List<Double> first24Hours = (rawTemps != null)
                ? rawTemps.subList(0, Math.min(rawTemps.size(), 24))
                : List.of();

        List<String> rawTimes = (hourly != null) ? hourly.time() : null;
        List<String> first24Times = (rawTimes != null)
                ? rawTimes.subList(0, Math.min(rawTimes.size(), 24))
                : List.of();

        double maxTemp = first24Hours.stream()
                .mapToDouble(Double::doubleValue)
                .max()
        .orElse(Double.MAX_VALUE);

        double minTemp = first24Hours.stream()
                .mapToDouble(Double::doubleValue)
                .min()
                .orElse(Double.MIN_VALUE);


        System.out.println("First 24 hour temperature: " + first24Times.get(0));
        System.out.println("First 24 hour Minimum Temp: " + minTemp);
        System.out.println("First 24 hour Maximum Temp: " + maxTemp);

        return new WeatherData(city, LocalDate.now(), currentTemp,  maxTemp, first24Hours, first24Times);
    }

    public OpenMeteoResponse getWeatherForBudapest() {
        return weatherApiClient.fetchWeather(47.4979, 19.0402);
    }
}