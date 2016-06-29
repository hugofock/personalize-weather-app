package com.pwa.model;

import com.pwa.builder.CityBuilder;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CityTest {

    @Test
    public void buildWithAllInformation() {
        DateTime dateTime = DateTime.now();

        City city = new CityBuilder().withId("id")
            .withSearchId(12345)
            .withName("name")
            .withCountry("country")
            .withVersion(1L)
            .withCreatedBy("createdby")
            .withCreatedDate(dateTime)
            .withLastModifiedBy("modifiedby")
            .withLastModifiedDate(dateTime)
            .build();
        assertEquals("id", city.getId());
        assertEquals(Integer.valueOf(12345), city.getSearchId());
        assertEquals("name", city.getName());
        assertEquals("country", city.getCountry());

        assertEquals(Long.valueOf(1L), city.getVersion());
        assertEquals("createdby", city.getCreatedBy());
        assertEquals(dateTime, city.getCreatedDate());
        assertEquals("modifiedby", city.getLastModifiedBy());
        assertEquals(dateTime, city.getLastModifiedDate());

        assertEquals("City:id", city.toString());
        City copyCity = city;
        assertEquals(copyCity.toJsonString(), city.toJsonString());
        assertEquals(city.hashCode(), copyCity.hashCode());
        assertTrue(city.equals(copyCity));
    }

}