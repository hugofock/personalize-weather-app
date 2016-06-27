package com.pwa.builder.weather;

import com.pwa.model.weather.Main;
import com.pwa.model.weather.Weather;
import com.pwa.model.weather.WeatherInfo;
import com.pwa.model.weather.Wind;

import java.util.List;

public class WeatherInfoBuilder {

    private List<Weather> weather;

    private Main main;

    private Wind wind;

    private String dt;

    private String id;

    private String name;

    public WeatherInfo build() {
        return new WeatherInfo(weather, main, wind, dt, id, name);
    }

    public WeatherInfoBuilder withWeather(List<Weather> weather) {
        this.weather = weather;
        return this;
    }

    public WeatherInfoBuilder withMain(Main main) {
        this.main = main;
        return this;
    }

    public WeatherInfoBuilder withWind(Wind wind) {
        this.wind = wind;
        return this;
    }

    public WeatherInfoBuilder withDt(String dt) {
        this.dt = dt;
        return this;
    }

    public WeatherInfoBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public WeatherInfoBuilder withName(String name) {
        this.name = name;
        return this;
    }
}