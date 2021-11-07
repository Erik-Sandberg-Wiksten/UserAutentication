package com.example.userauthentication;

import com.example.userauthentication.repository.DbConnector;
import com.example.userauthentication.repository.MapDbConnector;
import com.example.userauthentication.repository.MapDbRepository;
import com.example.userauthentication.repository.UserRepository;
import com.example.userauthentication.security.UUIDAuthenticationService;
import com.example.userauthentication.security.UserAuthenticationService;
import com.example.userauthentication.user.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public UserAuthenticationService userAuthenticationService() {
        return new UUIDAuthenticationService(userService(), userDataService());
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new MapDbRepository(dbConnector());
    }

    @Bean
    public UserLoginDataService userDataService() {
        return new UserLoginDataServiceImpl(userRepository());
    }

    @Bean
    public DbConnector dbConnector() {
        return new MapDbConnector();
    }


}
