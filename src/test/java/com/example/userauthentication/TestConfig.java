package com.example.userauthentication;

import com.example.userauthentication.repository.InMemoryRepository;
import com.example.userauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Configuration
public class TestConfig {

    @Autowired
    private WebApplicationContext wac;

    @Bean
    public UserRepository userRepository() {
        return new InMemoryRepository();
    }

    @Bean
    public MockMvc mockMvc() {
        return MockMvcBuilders
                .webAppContextSetup(wac)
                .alwaysDo(print())
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }
}
