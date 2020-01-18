package com.jslubowski.mainservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
public class Weather {

    @Getter
    private final String city;
    @Getter
    private final double pressure;
    @Getter
    private final int clouds;
    @Getter
    private final double windSpeed;
    @Getter
    private final String sunset;
    @Getter
    private final String sunrise;
    @Getter
    private final double temperature;
}
