package com.jslubowski.mainservice.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jslubowski.mainservice.service.JsonWeatherParserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public JsonWeatherParserService getWeatherServiceInstance(ObjectMapper mapper){
        return new JsonWeatherParserService(mapper);
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
