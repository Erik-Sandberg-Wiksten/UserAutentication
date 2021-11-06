package com.example.userauthentication.repository;

import com.example.userauthentication.user.UserLoginData;
import com.example.userauthentication.user.User;
import org.mapdb.DB;

import java.util.concurrent.ConcurrentMap;

public interface DbConnector {
    DB getDbConnection();

    void closeDbConnection(DB db);

    ConcurrentMap<String, User> getUsers(DB db);

    ConcurrentMap<String, UserLoginData> getUserData(DB db);
}
