package com.jslubowski.mainservice.service;

import com.jslubowski.mainservice.model.TodoEvent;
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

    public String getWeatherForecastForEvent(Long id, String username){
        TodoEvent event = todoEventService.getTodoEventForUser(id, username);
        String location = event.getLocation();

        String jsonAnswer = webClientBuilder.build()
                .get()  // HTTP GET Request
                .uri(URI + location + URI_END + apiKey)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return jsonAnswer;
    }

}
