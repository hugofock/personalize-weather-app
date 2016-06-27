package com.pwa.listener;

import com.pwa.builder.CityBuilder;
import com.pwa.common.listener.EntityListener;
import com.pwa.model.City;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/appcontext.xml", "classpath:/spring/appcontext-controller.xml" })
public class EntityListenerTest {

    @Test
    public void testEntityListenerPrePersistWithoutId() throws Exception {
        EntityListener entityListener = new EntityListener();

        City city = new CityBuilder().withSearchId(12345).withName("name").withCountry("country").build();

        entityListener.prePersistOrUpdate(city);

        assertNotNull(city.getId());
        assertNotNull(city.getCreatedBy());
        assertThat(city.getCreatedBy(), is("SYSTEM"));
        assertNotNull(city.getCreatedDate());
        assertNotNull(city.getLastModifiedBy());
        assertThat(city.getLastModifiedBy(), is("SYSTEM"));
        assertNotNull(city.getLastModifiedDate());
    }

    @Test
    public void testEntityListenerPrePersistId() throws Exception {
        EntityListener entityListener = new EntityListener();

        City city = new CityBuilder().withId("id").withSearchId(12345).withName("name").withCountry("country").build();

        entityListener.prePersistOrUpdate(city);

        assertNotNull(city.getId());
        assertThat(city.getId(), is("id"));
        assertNull(city.getCreatedBy());
        assertNull(city.getCreatedDate());
        assertNotNull(city.getLastModifiedBy());
        assertThat(city.getLastModifiedBy(), is("SYSTEM"));
        assertNotNull(city.getLastModifiedDate());
    }
}