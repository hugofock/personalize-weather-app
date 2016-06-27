package com.pwa.service.impl;

import com.pwa.common.exception.UnexpectedException;
import com.pwa.service.IWeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/appcontext.xml", "classpath:/spring/appcontext-controller.xml" })
@WebAppConfiguration
public class WeatherServiceIT {

    @Autowired
    private IWeatherService weatherService;

    @Test(expected = UnexpectedException.class)
    public void testCacheManagerInWeatherService() throws Exception {

        changeFinalStaticValue(WeatherService.class.getField("CONFIG_PROPERTIES"), "test");

        weatherService.findWeather(111);

    }

    static void changeFinalStaticValue(Field field, Object value) throws Exception {

        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, value);
    }

}