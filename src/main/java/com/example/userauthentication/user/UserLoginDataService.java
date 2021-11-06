package com.example.userauthentication.user;

import java.util.List;
import java.util.Optional;

public interface UserLoginDataService {
    Optional<UserLoginData> find(String token);

    void saveLogin(String token, String usernam);

    Optional<List<UserLoginData>> getLastLogins(String username);
}
