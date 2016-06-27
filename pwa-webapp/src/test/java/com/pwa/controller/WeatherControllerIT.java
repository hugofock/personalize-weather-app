package com.pwa.controller;

import com.pwa.common.constant.MimeType;
import com.pwa.service.ICityService;
import com.pwa.service.IWeatherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/appcontext.xml", "classpath:/spring/appcontext-controller.xml" })
@WebAppConfiguration
public class WeatherControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testWeatherAPIReturnCorrectResults() throws Exception {

        mockMvc.perform(get("/weather/weatherInfo/Sydney"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.JSON))
            .andExpect(jsonPath("$.status", is("Success")))
            .andExpect(jsonPath("$.object.weather", hasSize(1)))
            .andExpect(jsonPath("$.object.id", is("2147714")))
            .andExpect(jsonPath("$.object.name", is("Sydney")));
    }

    @Test
    public void testWhenCityNotFound() throws Exception {

        mockMvc.perform(get("/weather/weatherInfo/test"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.JSON))
            .andExpect(jsonPath("$.status", is("Failure")))
            .andExpect(jsonPath("$.message", is("Invalid Weather Info Requested")));
    }


    @Test
    public void testFindAllCity() throws Exception {

        mockMvc.perform(get("/weather/findAllCity"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.JSON))
            .andExpect(jsonPath("$.status", is("Success")))
            .andExpect(jsonPath("$.object[0].name", is("Sydney")));
    }
}