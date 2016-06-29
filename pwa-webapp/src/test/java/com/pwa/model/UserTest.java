package com.pwa.model;

import com.pwa.builder.UserBuilder;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {

    @Test
    public void buildWithAllInformation() {
        DateTime dateTime = DateTime.now();

        User user = new UserBuilder().withId("id")
            .withPassword("password")
            .withRole("role")
            .withUsername("username")
            .withVersion(1L)
            .withCreatedBy("createdby")
            .withCreatedDate(dateTime)
            .withLastModifiedBy("modifiedby")
            .withLastModifiedDate(dateTime)
            .build();
        assertEquals("id", user.getId());
        assertEquals("password", user.getPassword());
        assertEquals("role", user.getRole());
        assertEquals("username", user.getUsername());

        assertEquals(Long.valueOf(1L), user.getVersion());
        assertEquals("createdby", user.getCreatedBy());
        assertEquals(dateTime, user.getCreatedDate());
        assertEquals("modifiedby", user.getLastModifiedBy());
        assertEquals(dateTime, user.getLastModifiedDate());

        assertEquals("User:id", user.toString());
        User copyUser = user;
        assertEquals(copyUser.toJsonString(), user.toJsonString());
        assertEquals(user.hashCode(), copyUser.hashCode());
        assertTrue(user.equals(copyUser));

    }

}