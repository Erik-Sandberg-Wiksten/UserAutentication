package com.example.userauthentication.security;

import com.example.userauthentication.user.User;

import java.util.Optional;

public interface UserAuthenticationService {

    Optional<String> login(String username, String password);

    Optional<User> findByToken(String token);
}
