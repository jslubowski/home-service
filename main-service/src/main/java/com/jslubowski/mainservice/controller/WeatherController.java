package com.jslubowski.mainservice.controller;

import com.jslubowski.mainservice.service.WeatherService;
import com.jslubowski.mainservice.util.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class WeatherController {


    private final WeatherService weatherService;

    @GetMapping("/user/events/{id}/weather")
    public String getWeatherForecast(@PathVariable Long id, Principal principal){
        String userName = Utilities.currentUserName(principal);
        return weatherService.getWeatherForTodayLocation(id, userName).toString();
    }

}
