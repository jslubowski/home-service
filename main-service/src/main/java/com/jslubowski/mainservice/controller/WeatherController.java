package com.jslubowski.mainservice.controller;

import com.jslubowski.mainservice.model.TodoEvent;
import com.jslubowski.mainservice.service.TodoEventService;
import com.jslubowski.mainservice.util.ReadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class WeatherController {

    private final static String URI     = "https://api.weatherbit.io/v2.0/forecast/daily?city=";
    private final static String URI_END = "&key=";

    /*
    This controller uses weatherbit.io API Endpoints in order to receive weather forecast
     */

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private TodoEventService todoEventService;


    // -------------------- Get weather for specific date and city -------------------------
    // TODO
    @RequestMapping("/user/events/{id}/weather")
    public String getWeatherForecast(@PathVariable("id") Long id){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        TodoEvent event = todoEventService.getTodoEventForUser(id, username);
        String location = event.getLocation();

        String apiKey = ReadFile.readApiKeyFromTextFile();

        String jsonAnswer = webClientBuilder.build()
                .get()  // HTTP GET Request
                .uri(URI + location + URI_END + apiKey)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return jsonAnswer;
    }

}
