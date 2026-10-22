package org.example.weatherapp.client;


import org.example.weatherapp.model.GeocodingResponse;
import org.example.weatherapp.model.OpenMeteoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


@Component
public class WeatherApiClient {

    @Value("${weather.api.geocoding-url}")
    private String geocodingUrl;

    @Value("${weather.api.forecast-url}")
    private String forecastUrl;

    private final RestClient restClient;

    private GeocodingResponse geocodingResponse;

    public WeatherApiClient() {
        this.restClient = RestClient.create();
    }

    public OpenMeteoResponse fetchWeather(double lat, double lon) {
        //String url = "https://invalid-api-domain-12345.com";
        String url = "https://api.open-meteo.com/v1/forecast?latitude="
                + lat + "&longitude=" + lon + "&current_weather=true";

        return restClient.get()
                .uri(url)
                .retrieve() // 3. EXECUTE NETWORK CALL HERE! (Sends HTTP request across internet)
                .body(OpenMeteoResponse.class);
    }

    public GeocodingResponse fetchCoordinates(String city)
    {
        String url = "https://geocoding-api.open-meteo.com/v1/search?name="
                + city + "&count=1";
        return restClient.get()
                .uri(url)
                .retrieve()
                .body(GeocodingResponse.class);
    }
}
