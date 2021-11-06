package com.example.userauthentication.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserLoginData implements Serializable
{
    private static final long serialVersionUID = 1;


    private String username;
    private LocalDateTime loginDate;

    public UserLoginData(String username, LocalDateTime loginDate) {
        this.username = username;
        this.loginDate = loginDate;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getLoginDate() {
        return loginDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginData userLoginData = (UserLoginData) o;
        return Objects.equals(username, userLoginData.username) && Objects.equals(loginDate, userLoginData.loginDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, loginDate);
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "username='" + username + '\'' +
                ", loginDate=" + loginDate +
                '}';
    }
}
