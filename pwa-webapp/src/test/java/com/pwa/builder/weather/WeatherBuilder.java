package com.pwa.builder.weather;

import com.pwa.model.weather.Weather;

public class WeatherBuilder {

    private String id;
    private String main;
    private String description;
    private String icon;

    public Weather build() {
        return new Weather(id, main, description, icon);
    }

    public WeatherBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public WeatherBuilder withMain(String main) {
        this.main = main;
        return this;
    }

    public WeatherBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public WeatherBuilder withIcon(String icon) {
        this.icon = icon;
        return this;
    }
}
