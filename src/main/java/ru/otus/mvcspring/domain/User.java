package ru.otus.mvcspring.domain;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    private String id;
    private String userName;

    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(){}

    public User(User user) {
    this.id = user.id;
    this.userName = user.getUsername();
    this.password = user.getPassword();
    }

    public String getUsername() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }
}

