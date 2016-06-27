package com.pwa.builder.weather;

import com.pwa.model.weather.Main;

public class MainBuilder {

    private String temp;
    private String pressure;
    private String humidity;
    private String temp_min;
    private String temp_max;

    public Main build() {
        return new Main(temp, pressure, humidity, temp_min, temp_max);
    }

    public MainBuilder withTemp(String temp) {
        this.temp = temp;
        return this;
    }

    public MainBuilder withPressure(String pressure) {
        this.pressure = pressure;
        return this;
    }

    public MainBuilder withHumidity(String humidity) {
        this.humidity = humidity;
        return this;
    }

    public MainBuilder withTemp_min(String temp_min) {
        this.temp_min = temp_min;
        return this;
    }

    public MainBuilder withTemp_max(String temp_max) {
        this.temp_max = temp_max;
        return this;
    }
}
