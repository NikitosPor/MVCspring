package ru.otus.mvcspring.domain;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    private String id;
    private String userName;
    private String password;
    private String role;

    public User(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public User(){}

    public User(User user) {
    this.id = user.id;
    this.userName = user.getUsername();
    this.password = user.getPassword();
    this.role = user.getRole();
    }

    public String getUsername() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return this.role;
    }
}

