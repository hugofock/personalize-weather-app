package com.pwa.builder.weather;

import com.pwa.model.weather.Wind;

public class WindBuilder {

    private String speed;
    private String deg;

    public Wind build() {
        return new Wind(speed, deg);
    }

    public WindBuilder withSpeed(String speed) {
        this.speed = speed;
        return this;
    }

    public WindBuilder withDeg(String deg) {
        this.deg = deg;
        return this;
    }
}
