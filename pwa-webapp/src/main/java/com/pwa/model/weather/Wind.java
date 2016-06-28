package com.pwa.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Wind {

    private String speed;
    @JsonIgnore
    private String deg;

    public Wind() {

    }

    public Wind(String speed, String deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }
}
