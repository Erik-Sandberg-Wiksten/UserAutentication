package com.example.userauthentication.controllers;

import com.example.userauthentication.TestConfig;
import com.example.userauthentication.UserAuthenticationApplication;
import com.example.userauthentication.user.User;
import com.example.userauthentication.user.UserLoginDataService;
import com.example.userauthentication.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@ContextConfiguration(classes = { UserAuthenticationApplication.class, TestConfig.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
class UserOperationControllerTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserLoginDataService userLoginDataService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/users/current with an unknown token should return HTTP 401")
    public void currentUnknownToken() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/current")
                        .header("Authorization", "Bearer 1337")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    @DisplayName("/users/current with a known token should return HTTP 200 and list of maximum 10 last logins")
    public void currentKnownToken() throws Exception {
        User user = new User("Steve", "Ender dragons are scary");
        userService.save(user);
        userLoginDataService.saveLogin("1337", "Steve");

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/current")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer 1337")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(lessThanOrEqualTo(10))));
    }

}