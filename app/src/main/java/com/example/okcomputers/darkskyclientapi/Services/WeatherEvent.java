package com.example.okcomputers.darkskyclientapi.Services;

import Models.Weather;

/**
 * Created by OK Computers on 10/10/2018.
 */

public class WeatherEvent {

    private final Weather weather;

    public WeatherEvent(Weather weather) {
        this.weather = weather;
    }
    public Weather getWeather() {
        return weather;
    }
}
