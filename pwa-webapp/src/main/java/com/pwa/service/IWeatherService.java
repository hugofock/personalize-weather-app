package com.pwa.service;

import com.pwa.common.exception.UnexpectedException;
import com.pwa.model.weather.WeatherInfo;

public interface IWeatherService extends BaseService {
    WeatherInfo findWeather(int id) throws UnexpectedException;
}
