package com.jslubowski.mainservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jslubowski.mainservice.model.Weather;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JsonWeatherParserService {

    final static String JSON_NODE = "data";
    final ObjectMapper objectMapper;

    public JsonNode extractDataNode(String jsonResponse){
        try{
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            JsonNode dataNode = jsonNode.get(JSON_NODE).get(0);
            return dataNode;
        }catch(IOException e){
            System.out.println("Couldn't parse jsonResponse." + e.getMessage());
        }
        return null;
    }

    public Weather getWeatherObject(JsonNode node){
        return Weather.builder()
                .city(node.get("city_name").toString().replace("\"", ""))
                .pressure(Double.parseDouble(node.get("pres").toString()))
                .clouds(Integer.parseInt(node.get("clouds").toString()))
                .windSpeed(Double.parseDouble(node.get("wind_spd").toString()))
                .sunset(node.get("sunset").toString().replace("\"", ""))
                .sunrise(node.get("sunrise").toString().replace("\"", ""))
                .temperature(Double.parseDouble(node.get("temp").toString()))
                .build();
    }

    public String convertWeatherToJson(Weather weather){
        try{
            return objectMapper.writeValueAsString(weather);
        }catch(JsonProcessingException e){
            log.error("Couldn't parse Weather to JSON");
        }
        return null;
    }

}
