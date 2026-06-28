package com.yarzarnayoo.weather.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class OpenWeatherClient {

    private final RestClient restClient;
    private final String apiKey;

    public OpenWeatherClient(
            RestClient.Builder builder,
            @Value("${weather.api.base-url}") String baseUrl,
            @Value("${weather.api.key}") String apiKey
    ) {
        this.restClient = builder.baseUrl(baseUrl).build();
        this.apiKey = apiKey;
    }

    public String getWeatherRaw(String city) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/data/2.5/weather")
                        .queryParam("q", city)
                        .queryParam("appid", apiKey)
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .body(String.class);
    }
}