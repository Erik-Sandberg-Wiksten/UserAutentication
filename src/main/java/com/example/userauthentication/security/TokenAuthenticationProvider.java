package com.example.userauthentication.security;

import com.example.userauthentication.user.UserAuthenticationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

    private UserAuthenticationService authService;

    public TokenAuthenticationProvider(UserAuthenticationService authService) {
        this.authService = authService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        // Nothing to do
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken auth) throws AuthenticationException {
        Object token = auth.getCredentials();
        return Optional.ofNullable(token)
                .map(String::valueOf)
                .flatMap(authService::findByToken)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));
    }
}
