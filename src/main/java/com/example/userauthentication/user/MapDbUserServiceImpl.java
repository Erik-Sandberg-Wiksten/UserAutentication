package com.example.userauthentication.user;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

public class MapDbUserServiceImpl implements UserService{

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public Optional<User> find(String id) {
        DB db = getDbConnection();
        ConcurrentMap<String, User> users = getUsers(db);

        Optional<User> user = Optional.ofNullable(users.get(id));

        closeDbConnection(db);

        return user;
    }

    private DB getDbConnection() {
        return DBMaker
                .fileDB("user-auth.db")
                .fileMmapEnable()
                .make();
    }

    private void closeDbConnection(DB db) {
        db.close();
    }

    private ConcurrentMap<String, User> getUsers(DB db) {
        return (ConcurrentMap<String, User>) db
                .hashMap("users")
                .createOrOpen();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        DB db = getDbConnection();
        ConcurrentMap<String, User> users = getUsers(db);

        Optional<User> user = users
                .values()
                .stream()
                .filter(u -> Objects.equals(username, u.getUsername()))
                .findFirst();

        closeDbConnection(db);

        return user;
    }
}
