package com.example.userauthentication.controllers;

import com.example.userauthentication.user.User;
import com.example.userauthentication.security.UserAuthenticationService;
import com.example.userauthentication.user.UserLoginData;
import com.example.userauthentication.user.UserLoginDataService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserOperationController {

    private UserAuthenticationService authentication;
    private UserLoginDataService userLoginDataService;

    public UserOperationController(UserAuthenticationService authentication, UserLoginDataService userLoginDataService) {
        this.authentication = authentication;
        this.userLoginDataService = userLoginDataService;
    }

    @GetMapping("/current")
    List<UserLoginData> getCurrent(@AuthenticationPrincipal final User user) {
        String username = user.getUsername();

        return userLoginDataService.getLastLogins(username).orElse(Collections.emptyList());
    }

}
