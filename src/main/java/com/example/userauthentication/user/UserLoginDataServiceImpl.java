package com.example.userauthentication.user;

import com.example.userauthentication.repository.UserRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserLoginDataServiceImpl implements UserLoginDataService {

    private UserRepository repository;

    public UserLoginDataServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<UserLoginData> find(String token) {
        return repository.findLoginData(token);
    }

    @Override
    public void saveLogin(String token, String username) {
       repository.saveLogin(token, username);
    }

    @Override
    public Optional<List<UserLoginData>> getLastLogins(String username) {

        Optional<List<UserLoginData>> userLogins = repository.getLogins(username);

        if(userLogins.isEmpty())
        {
            return Optional.empty();
        }

        List<UserLoginData> lastTenUserLogins = userLogins.get()
                .stream()
                .sorted(Comparator.comparing(UserLoginData::getLoginDate).reversed())
                .limit(10)
                .collect(Collectors.toList());

        return Optional.of(lastTenUserLogins);
    }
}
