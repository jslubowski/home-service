package com.jslubowski.mainservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jslubowski.mainservice.model.Weather;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class JsonWeatherParserServiceTest {

    JsonWeatherParserService jsonWeatherParserService;
    String jsonResponse;
    TestReporter testReporter;
    TestInfo testInfo;

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter){
        this.testInfo = testInfo;
        this.testReporter = testReporter;
        this.jsonResponse = TestConstants.JSON_RESPONSE;
        this.jsonWeatherParserService = new JsonWeatherParserService(new ObjectMapper());
    }

    @Test
    @DisplayName("Testing Json Parsing")
    public void testWeatherDataExtraction(){
        String expected = "Weather(city=Warsaw, pressure=993.4, clouds=100, windSpeed=0.89, sunset=14:38, sunrise=06:44, temperature=5.6)";
        JsonNode node = jsonWeatherParserService.extractDataNode(jsonResponse);
        if(!(node == null)) {
            testReporter.publishEntry(node.toString());
        } else {
            testReporter.publishEntry("Node is empty");
            assertEquals(1,0); //mark test as failed
        }
        Weather weather = jsonWeatherParserService.getWeatherObject(node);
        testReporter.publishEntry(weather.toString());
        assertEquals(expected, weather.toString(), "Object doesn't match the JSON in TestConstants");
    }

}