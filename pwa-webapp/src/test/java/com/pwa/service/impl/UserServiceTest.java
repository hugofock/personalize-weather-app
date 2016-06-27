package com.pwa.service.impl;

import com.pwa.builder.UserBuilder;
import com.pwa.model.User;
import com.pwa.repository.UserRepository;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserRepository userRepositoryMock;

    private UserService userService;

    private User userModel;

    private DateTime dateTime;

    @Before
    public void setUp() {
        dateTime = DateTime.now();

        userRepositoryMock = mock(UserRepository.class);

        userService = new UserService(userRepositoryMock);

        userModel = new UserBuilder().withId("id")
            .withPassword("password")
            .withRole("role")
            .withUsername("username")
            .withVersion(1L)
            .withCreatedBy("createdby")
            .withCreatedDate(dateTime)
            .withLastModifiedBy("modifiedby")
            .withLastModifiedDate(dateTime)
            .build();
        ;

    }

    @Test
    public void testFindUserByUsername() throws Exception {

        when(userRepositoryMock.findUserByUsername("username")).thenReturn(userModel);
        assertEquals(userModel, userService.findUserByUsername("username"));

    }

    @Test
    public void testFindAllUser() throws Exception {

        List<User> userList = Arrays.asList(userModel, userModel);
        when(userRepositoryMock.findAll()).thenReturn(userList);
        assertEquals(userList, userRepositoryMock.findAll());

    }
}