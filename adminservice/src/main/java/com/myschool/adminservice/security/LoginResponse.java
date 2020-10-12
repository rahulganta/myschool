package com.myschool.adminservice.security;

import lombok.Data;

import java.util.List;

@Data
public class LoginResponse {
    private String token;

    private String tokenType = "Bearer";

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private List<String> roles;

    private long lastLogin;

    private String lang;

    private String theme;

    public LoginResponse(String token, String userName, String firstName, String lastName, String email, List<String> roles, long lastLogin, String lang, String theme) {
        this.token = token;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.lang = lang;
        this.theme = theme;
    }
}
