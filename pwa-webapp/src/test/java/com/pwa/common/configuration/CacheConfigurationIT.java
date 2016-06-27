package com.pwa.common.configuration;

import com.pwa.model.weather.WeatherInfo;
import com.pwa.service.IWeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/appcontext.xml", "classpath:/spring/appcontext-controller.xml" })
@WebAppConfiguration
public class CacheConfigurationIT {

    @Autowired
    private IWeatherService weatherService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testCacheManagerInWeatherService() throws Exception {

        weatherService.findWeather(2171507);
        WeatherInfo weatherInfo = cacheManager.getCache("weatherInfo").get(2171507, WeatherInfo.class);

        assertNotNull(weatherInfo);
        assertEquals(weatherInfo.getId(), "2171507");

    }
}