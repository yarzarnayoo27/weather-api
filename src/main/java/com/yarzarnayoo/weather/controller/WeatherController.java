package com.yarzarnayoo.weather.controller;

import com.yarzarnayoo.weather.dto.WeatherResponse;
import com.yarzarnayoo.weather.service.WeatherService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping
    public WeatherResponse getWeather(@RequestParam String city) {
        return service.getWeather(city);
    }
}