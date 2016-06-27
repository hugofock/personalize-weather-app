package com.pwa.model.weather;

import java.util.List;

public class WeatherInfo {

    private List<Weather> weather;

    private Main main;

    private Wind wind;

    private String dt;

    private String id;

    private String name;

    public WeatherInfo() {

    }

    public WeatherInfo(List<Weather> weather, Main main, Wind wind, String dt, String id, String name) {
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.dt = dt;
        this.id = id;
        this.name = name;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> Weather) {
        this.weather = Weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
