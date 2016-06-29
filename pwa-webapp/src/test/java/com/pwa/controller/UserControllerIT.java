package com.pwa.controller;

import com.pwa.common.constant.MimeType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:/spring/appcontext.xml", "classpath:/spring/appcontext-controller.xml", "classpath:/spring/appcontext-security.xml"
})
@WebAppConfiguration
public class UserControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Filter formAuthenticationFilter;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilters(formAuthenticationFilter).build();
    }

    @Test
    public void testUserController() throws Exception {
        mockMvc.perform(get("/user/users")).andExpect(status().isOk());
    }

    @Test
    public void testUserSecurityLoginSuccess() throws Exception {
        mockMvc.perform(
            post("/authenticate").param("username", "admin").param("password", "password").contentType(MediaType.APPLICATION_FORM_URLENCODED) //
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.JSON))
            .andExpect(jsonPath("$.status", is("Success")))
            .andExpect(jsonPath("$.url", is("http://localhost/weather")));
    }

    @Test
    public void testUserSecurityLoginFailure() throws Exception {
        mockMvc.perform(
            post("/authenticate").param("username", "admin").param("password", "password1").contentType(MediaType.APPLICATION_FORM_URLENCODED) //
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MimeType.JSON))
            .andExpect(jsonPath("$.status", is("Failure")))
            .andExpect(jsonPath("$.message", is("The email and password you entered don't match.")));
    }

}