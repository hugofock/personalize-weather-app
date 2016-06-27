package com.pwa.service.impl;

import com.pwa.builder.CityBuilder;
import com.pwa.model.City;
import com.pwa.repository.CityRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CityServiceTest {

    private CityRepository cityRepositoryMock;

    private CityService cityService;

    private City cityModel;

    @Before
    public void setUp() {
        cityRepositoryMock = mock(CityRepository.class);

        cityService = new CityService(cityRepositoryMock);

        cityModel = (new CityBuilder()).withId("id").withSearchId(1234).withName("name").withCountry("country").build();

    }

    @Test
    public void testFindCityByCityName() throws Exception {

        when(cityRepositoryMock.findCityByCityName("Sydney")).thenReturn(cityModel);
        assertEquals(cityModel, cityService.findCityByCityName("Sydney"));

    }

    @Test
    public void testFindAllCity() throws Exception {

        List<City> cityList = Arrays.asList(cityModel, cityModel);
        when(cityRepositoryMock.findAll()).thenReturn(cityList);
        assertEquals(cityList, cityRepositoryMock.findAll());

    }
}