package com.example.userauthentication.repository;

import com.example.userauthentication.user.User;
import com.example.userauthentication.user.UserLoginData;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findUser(String username);

    Optional<UserLoginData> findLoginData(String token);

    void saveLogin(String token, String username);

    Optional<List<UserLoginData>> getLogins(String username);
}
