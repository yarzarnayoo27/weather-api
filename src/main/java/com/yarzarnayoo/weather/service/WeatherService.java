package com.yarzarnayoo.weather.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yarzarnayoo.weather.client.OpenWeatherClient;
import com.yarzarnayoo.weather.dto.WeatherResponse;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final OpenWeatherClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WeatherService(OpenWeatherClient client) {
        this.client = client;
    }

    public WeatherResponse getWeather(String city) {

        String rawJson = client.getWeatherRaw(city);

        try {
            JsonNode root = objectMapper.readTree(rawJson);

            WeatherResponse response = new WeatherResponse();

            response.setCity(root.get("name").asText());
            response.setTemperature(root.get("main").get("temp").asDouble());
            response.setFeelsLike(root.get("main").get("feels_like").asDouble());
            response.setHumidity(root.get("main").get("humidity").asInt());
            response.setWindSpeed(root.get("wind").get("speed").asDouble());
            response.setDescription(
                    root.get("weather").get(0).get("description").asText()
            );

            return response;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse weather data");
        }
    }
}