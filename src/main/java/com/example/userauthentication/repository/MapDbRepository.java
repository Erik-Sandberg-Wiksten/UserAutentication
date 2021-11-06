package com.example.userauthentication.repository;

import com.example.userauthentication.user.User;
import com.example.userauthentication.user.UserLoginData;
import org.mapdb.DB;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class MapDbRepository implements UserRepository{
    DbConnector connector;

    public MapDbRepository(DbConnector connector) {
        this.connector = connector;
    }

    @Override
    public User save(User user) {
        DB db = connector.getDbConnection();
        ConcurrentMap<String, User> users = connector.getUsers(db);

        String username = user.getUsername();
        users.put(username, user);

        connector.closeDbConnection(db);

        return user;
    }

    @Override
    public Optional<User> findUser(String username) {
        DB db = connector.getDbConnection();
        ConcurrentMap<String, User> users = connector.getUsers(db);

        Optional<User> user = Optional.ofNullable(users.get(username));

        connector.closeDbConnection(db);

        return user;
    }

    @Override
    public Optional<UserLoginData> findLoginData(String token) {
        DB db = connector.getDbConnection();
        ConcurrentMap<String, UserLoginData> userLoginData = connector.getUserData(db);

        UserLoginData loginData = userLoginData.get(token);

        connector.closeDbConnection(db);

        return Optional.ofNullable(loginData);
    }

    @Override
    public void saveLogin(String token, String username) {
        DB db = connector.getDbConnection();
        ConcurrentMap<String, UserLoginData> userLoginData = connector.getUserData(db);

        userLoginData.put(token, new UserLoginData(username, LocalDateTime.now()));

        connector.closeDbConnection(db);
    }

    @Override
    public Optional<List<UserLoginData>> getLogins(String username) {
        DB db = connector.getDbConnection();
        ConcurrentMap<String, UserLoginData> userLoginData = connector.getUserData(db);

        List<UserLoginData> login = userLoginData.values()
                .stream()
                .filter(loginData -> username.equals(loginData.getUsername()))
                .collect(Collectors.toList());

        connector.closeDbConnection(db);

        return Optional.of(login);
    }
}
