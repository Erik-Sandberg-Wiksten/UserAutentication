package com.example.userauthentication.repository;

import com.example.userauthentication.user.User;
import com.example.userauthentication.user.UserLoginData;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.util.concurrent.ConcurrentMap;

public class MapDbConnector implements DbConnector{

    public DB getDbConnection() {
        return DBMaker
                .fileDB("user-auth.db")
                .fileMmapEnable()
                .make();
    }

    public void closeDbConnection(DB db) {
        db.close();
    }

    public ConcurrentMap<String, User> getUsers(DB db) {
        return (ConcurrentMap<String, User>) db
                .hashMap("users")
                .createOrOpen();
    }

    public ConcurrentMap<String, UserLoginData> getUserData(DB db) {
        return (ConcurrentMap<String, UserLoginData>) db
                .hashMap("user-login-data")
                .createOrOpen();
    }
}
