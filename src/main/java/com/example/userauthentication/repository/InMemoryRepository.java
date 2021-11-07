package com.example.userauthentication.repository;

import com.example.userauthentication.user.User;
import com.example.userauthentication.user.UserLoginData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class InMemoryRepository implements UserRepository{

    ConcurrentMap<String, User> users;
    ConcurrentMap<String, UserLoginData> userLoginData;

    public InMemoryRepository() {
        this.users = new ConcurrentHashMap<>();
        this.userLoginData = new ConcurrentHashMap<>();
    }

    @Override
    public User save(User user) {
        users.put(user.getUsername(), user);
        return user;
    }

    @Override
    public Optional<User> findUser(String username) {
        return Optional.ofNullable(users.get(username));
    }

    @Override
    public Optional<UserLoginData> findLoginData(String token) {
        return Optional.ofNullable(userLoginData.get(token));
    }

    @Override
    public void saveLogin(String token, String username) {
        userLoginData.put(token, new UserLoginData(username, LocalDateTime.now()));
    }

    @Override
    public Optional<List<UserLoginData>> getLogins(String username) {
        List<UserLoginData> login = userLoginData.values()
                .stream()
                .filter(loginData -> username.equals(loginData.getUsername()))
                .collect(Collectors.toList());

        return Optional.of(login);
    }
}
