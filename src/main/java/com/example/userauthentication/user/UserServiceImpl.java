package com.example.userauthentication.user;

import com.example.userauthentication.repository.UserRepository;

import java.util.Optional;

public class UserServiceImpl implements UserService{

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {

        if(find(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistAuthenticationException("User already exists!");
        }

        return repository.save(user);
    }

    @Override
    public Optional<User> find(String username) {
        return repository.findUser(username);
    }
}
