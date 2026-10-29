/*package org.example.weatherapp.service;
import org.example.weatherapp.client.WeatherApiClient;
import org.example.weatherapp.model.GeocodingResponse;
import org.example.weatherapp.model.OpenMeteoResponse;
import org.example.weatherapp.model.WeatherData;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class WeatherServiceOLD {

    private final WeatherApiClient weatherApiClient;

    public WeatherServiceOLD(WeatherApiClient weatherApiClient) {
        this.weatherApiClient =  weatherApiClient;
    }

    public WeatherData getWeatherByCity(String city) {
        // Instantiate dummy data per request
        //double lat = 47.4979;
        //double lon = 19.0402;

        //Fetch dynamic coordinates
        GeocodingResponse geo = weatherApiClient.fetchCoordinates(city);

        //Validate city exists! (Fixes non-existent city crash)
        if (geo == null || geo.results() == null || geo.results().isEmpty()) {
            throw new IllegalArgumentException("City not found: " + city);
        }

        double lat = geo.results().get(0).latitude();
        double lon = geo.results().get(0).longitude();

        // Fetch real weather using dynamic coordinates
        OpenMeteoResponse weather = weatherApiClient.fetchWeather(lat, lon);

        if (weather == null || weather.currentWeather() == null) {
            throw new IllegalStateException("Failed to parse weather payload from external API");
        }

        double currentTemp = weather.currentWeather().temperature();

        // Extract real hourly temperatures array from Open-Meteo payload!
        List<Double> hourlyTemps = weather.hourly().temperature2m();
        List<Double> first24Hours = hourlyTemps.subList(0, Math.min(hourlyTemps.size(), 24));


        return new WeatherData(city, LocalDate.now(), currentTemp, currentTemp + 5.0, first24Hours);
    }


    public OpenMeteoResponse getWeatherForBudapest() {
        // We explicitly execute method on injected bean!
        return weatherApiClient.fetchWeather(47.4979, 19.0402);
    }
}
*/