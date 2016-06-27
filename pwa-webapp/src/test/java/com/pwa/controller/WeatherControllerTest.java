package com.pwa.controller;

import com.pwa.builder.CityBuilder;
import com.pwa.builder.weather.MainBuilder;
import com.pwa.builder.weather.WeatherBuilder;
import com.pwa.builder.weather.WeatherInfoBuilder;
import com.pwa.builder.weather.WindBuilder;
import com.pwa.common.constant.MimeType;
import com.pwa.common.exception.UnexpectedException;
import com.pwa.common.vo.BasicResponseEntityVO;
import com.pwa.model.City;
import com.pwa.model.weather.WeatherInfo;
import com.pwa.service.ICityService;
import com.pwa.service.IWeatherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/appcontext.xml", "classpath:/spring/appcontext-controller.xml" })
@WebAppConfiguration
public class WeatherControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private IWeatherService weatherServiceMock;

    @Autowired
    private ICityService cityServiceMock;

    private WeatherInfo weatherInfoModel;

    private City cityModel;

    private WeatherController weatherController;

    @Before
    public void setUp() {

        weatherServiceMock = Mockito.mock(IWeatherService.class);
        cityServiceMock = Mockito.mock(ICityService.class);

        //reset mock between tests in Spring container
        Mockito.reset(weatherServiceMock);
        Mockito.reset(cityServiceMock);

        weatherController = new WeatherController(weatherServiceMock, cityServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();

        weatherInfoModel = (new WeatherInfoBuilder()).withWeather(
            Arrays.asList((new WeatherBuilder()).withId("id").withIcon("icon").withMain("main").withDescription("description").build()))
            .withMain((new MainBuilder()).withHumidity("humidity")
                          .withPressure("pressure")
                          .withTemp("temp")
                          .withTemp_max("temp_max")
                          .withTemp_min("temp_min")
                          .build())
            .withWind((new WindBuilder()).withDeg("deg").withSpeed("7.7").build())
            .withDt("1467050826")
            .withId("id")
            .withName("name")
            .build();

        cityModel = (new CityBuilder()).withId("id").withSearchId(1234).withName("name").withCountry("country").build();

    }

    @Test
    public void testWithEmptyCityName() throws Exception {

        mockMvc.perform(get("/weather/weatherInfo/")).andExpect(status().isNotFound());
    }

    @Test
    public void testWithIncorrectCityName() throws Exception {

        mockMvc.perform(get("/weather/weatherInfo/test"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.JSON))
            .andExpect(jsonPath("$.status", is("Failure")))
            .andExpect(jsonPath("$.message", is("Invalid Weather Info Requested")));
    }

    @Test
    public void testCityWeatherServicesInvoke() throws Exception {

        when(cityServiceMock.findCityByCityName("Sydney")).thenReturn(cityModel);

        when(weatherServiceMock.findWeather(1234)).thenReturn(weatherInfoModel);

        mockMvc.perform(get("/weather/weatherInfo/Sydney"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.JSON))
            .andExpect(jsonPath("$.status", is("Success")))
            .andExpect(jsonPath("$.object.weather", hasSize(1)))
            .andExpect(jsonPath("$.object.id", is("id")))
            .andExpect(jsonPath("$.object.name", is("name")));
//            .andExpect(jsonPath("$.object.dt", is("2016-06-28 02:07:06")))
//            .andExpect(jsonPath("$.object.wind.speed", is("27.72")));

        verify(cityServiceMock, times(1)).findCityByCityName("Sydney");

        verify(weatherServiceMock, times(1)).findWeather(1234);

        verifyNoMoreInteractions(cityServiceMock);
        verifyNoMoreInteractions(weatherServiceMock);

    }

    @Test
    public void testHandleExceptionThrow() throws Exception {

        when(cityServiceMock.findCityByCityName("Sydney")).thenReturn(cityModel);

        when(weatherServiceMock.findWeather(1234)).thenThrow(new UnexpectedException("exception test", new Exception()));

        ResponseEntity<BasicResponseEntityVO> responseEntityVOResponseEntity = weatherController.weatherInfo("Sydney");

        assertThat("Failure", is(responseEntityVOResponseEntity.getBody().getStatus()));
        assertThat("Invalid Weather Info Requested", is(responseEntityVOResponseEntity.getBody().getMessage()));

    }


}