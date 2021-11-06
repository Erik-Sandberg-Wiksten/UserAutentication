package com.example.userauthentication.user;

import java.util.Optional;


public interface UserService {
    User save(User user);

    Optional<User> find(String username);


}
