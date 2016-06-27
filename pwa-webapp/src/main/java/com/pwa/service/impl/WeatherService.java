package com.pwa.service.impl;

import com.pwa.common.exception.UnexpectedException;
import com.pwa.model.weather.WeatherInfo;
import com.pwa.service.IWeatherService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.Properties;

@Service
@Transactional(readOnly = false)
public class WeatherService implements IWeatherService {

    public static final String CONFIG_PROPERTIES = "config.properties";
    public static final String OPENWEATHERMAP_APPID = "openweathermap_appid";
    public static final String OPENWEATHERMAP_UNTIS = "openweathermap_units";

    @Cacheable(value = "weatherInfo", cacheManager = "cacheManager")
    public WeatherInfo findWeather(int id) throws UnexpectedException {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = WeatherService.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES);

            prop.load(input);

            if (StringUtils.isEmpty(prop.getProperty(OPENWEATHERMAP_APPID)) || StringUtils.isEmpty(prop.getProperty(OPENWEATHERMAP_UNTIS))) {
                throw new UnexpectedException("Empty openweathermap_appid or openweathermap_units in config.properties, Country Id:" + id);
            }

            RestTemplate restTemplate = new RestTemplate();

            LOGGER.debug("retrieve current data from weather API");

            WeatherInfo weatherInfo = restTemplate.getForObject(
                String.format("http://api.openweathermap.org/data/2.5/weather?id=%1s&units=%2s&APPID=%3s", id, prop.getProperty(OPENWEATHERMAP_UNTIS),
                              prop.getProperty(OPENWEATHERMAP_APPID)), WeatherInfo.class);

            if (StringUtils.isEmpty(weatherInfo.getId())) {
                throw new UnexpectedException("Empty weatherInfo return from openweathermap, Country Id:" + id);
            }

            return weatherInfo;

        } catch (Throwable ex) {

            throw new UnexpectedException("Error in WeatherService, Country Id:" + id, ex);

        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Throwable e) {
                    throw new UnexpectedException("Error in WeatherService, Country Id:" + id, e);
                }
            }
        }
    }

}
