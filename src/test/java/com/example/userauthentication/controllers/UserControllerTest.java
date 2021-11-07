package com.example.userauthentication.controllers;

import com.example.userauthentication.TestConfig;
import com.example.userauthentication.UserAuthenticationApplication;
import com.example.userauthentication.user.User;
import com.example.userauthentication.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@ContextConfiguration(classes = { UserAuthenticationApplication.class, TestConfig.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @Autowired
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/public/users/register an already existing user should return HTTP 409 and message")
    public void userAlreadyExists() throws Exception {
        User user = new User("Master Chief", "The helmet stays on");
        userService.save(user);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/public/users/register")
                        .param("username", "Master Chief")
                        .param("password", "The helmet stays on")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(content().string("User already exists!"));

    }

    @Test
    @DisplayName("/public/users/register a new user should return HTTP 200 and welcome message")
    public void registeringNewUser() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/public/users/register")
                        .param("username", "Kratos")
                        .param("password", "Boi")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to our club Kratos"));

    }

    @Test
    @DisplayName("/public/users/login an unknown user should return HTTP 401 and message")
    public void loginUnknownUser() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/public/users/login")
                        .param("username", "Mario")
                        .param("password", "Shroooooms")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("invalid login and/or password"));

    }

    @Test
    @DisplayName("/public/users/login an known user should return HTTP 200 and non empty token")
    public void loginUser() throws Exception {
        User user = new User("Steve", "Ender dragons are scary");
        userService.save(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/public/users/login")
                        .param("username", "Steve")
                        .param("password", "Ender dragons are scary")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());

    }
}