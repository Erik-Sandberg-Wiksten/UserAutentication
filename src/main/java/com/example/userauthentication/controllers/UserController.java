package com.example.userauthentication.controllers;

import com.example.userauthentication.user.User;
import com.example.userauthentication.security.UserAuthenticationService;
import com.example.userauthentication.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/users")
public class UserController {

    private UserAuthenticationService authentication;
    private UserService users;

    public UserController(UserAuthenticationService authentication, UserService users) {
        this.authentication = authentication;
        this.users = users;
    }

    @PostMapping("/register")
    ResponseEntity<String> register(
            @RequestParam("username") final String username,
            @RequestParam("password") final String password) {
        User user = users
                .save(
                        new User(username, password)
                );

        return ResponseEntity.ok("Welcome to our club " + user.getUsername());
    }

    @PostMapping("/login")
    String login(
            @RequestParam("username") final String username,
            @RequestParam("password") final String password) {
        return authentication
                .login(username, password)
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }
}
