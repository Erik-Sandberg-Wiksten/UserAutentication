package com.example.userauthentication.security;

import com.example.userauthentication.user.*;

import java.util.Optional;
import java.util.UUID;

public class UUIDAuthenticationService  implements UserAuthenticationService {

    private UserService users;
    private UserLoginDataService userLoginData;

    public UUIDAuthenticationService(UserService users, UserLoginDataService userLoginData) {
        this.users = users;
        this.userLoginData = userLoginData;
    }

    @Override
    public Optional<String> login(final String username, final String password) {
        final String uuid = UUID.randomUUID().toString();

        boolean userIsUnknown = users.find(username)
                .map(User::getPassword)
                .filter(password::equals)
                .isEmpty();

        if(userIsUnknown){
            return Optional.empty();
        }
        userLoginData.saveLogin(uuid, username);

        return Optional.of(uuid);
    }

    @Override
    public Optional<User> findByToken(final String token) {
        return userLoginData.find(token)
                .map(UserLoginData::getUsername)
                .flatMap(users::find);
    }
}
