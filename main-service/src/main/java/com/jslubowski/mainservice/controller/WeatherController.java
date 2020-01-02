package com.jslubowski.mainservice.controller;

import com.jslubowski.mainservice.model.TodoEvent;
import com.jslubowski.mainservice.service.TodoEventService;
import com.jslubowski.mainservice.service.WeatherService;
import com.jslubowski.mainservice.util.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class WeatherController {


    private final WeatherService weatherService;

    @GetMapping("/user/events/{id}/weather")
    public String getWeatherForecast(@PathVariable Long id, Principal principal){
        String userName = Utilities.currentUserName(principal);
        return weatherService.getWeatherForecastForEvent(id, userName);
    }

}
