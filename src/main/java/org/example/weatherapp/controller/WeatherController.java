package org.example.weatherapp.controller;

import org.example.weatherapp.model.OpenMeteoResponse;
import org.example.weatherapp.model.WeatherData;
import org.example.weatherapp.service.WeatherService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService)
    {   this.weatherService = weatherService;   }

    @GetMapping("/{city}")
    public ResponseEntity<WeatherData> getWeatherData(@PathVariable String city)
        {
            WeatherData data = weatherService.getWeatherByCity(city);
            if (data == null) {
                return  ResponseEntity.status(404).body(null);
            }
            return ResponseEntity.ok(data);
        }

    @GetMapping("/raw")
    public OpenMeteoResponse getRawWeather()
    {   return weatherService.getWeatherForBudapest();  }
}
