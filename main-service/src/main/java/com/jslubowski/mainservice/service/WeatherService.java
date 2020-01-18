package com.jslubowski.mainservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.jslubowski.mainservice.model.TodoEvent;
import com.jslubowski.mainservice.model.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class WeatherService {

    @Value("${weather.uri}") private String URI;
    @Value("${api.key}") private String apiKey;
    @Value("${weather.uri.end}") private String URI_END;

    private final WebClient.Builder webClientBuilder;
    private final TodoEventService todoEventService;
    private final JsonWeatherParserService jsonWeatherParserService;

    public Weather getWeatherForTodayLocation(Long id, String username){
        TodoEvent event = todoEventService.getTodoEventForUser(id, username);
        String location = event.getLocation();
        String jsonAnswer = webClientBuilder.build()
                .get()  // HTTP GET Request
                .uri(URI + location + URI_END + apiKey)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        JsonNode node = jsonWeatherParserService.extractDataNode(jsonAnswer);
        Weather weather = jsonWeatherParserService.getWeatherObject(node);
        return weather;
    }

    public String getWeatherAsJson(Weather weather){
        return jsonWeatherParserService.convertWeatherToJson(weather);
    }

}
