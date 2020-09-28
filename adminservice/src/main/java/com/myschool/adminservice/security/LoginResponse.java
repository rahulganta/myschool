package com.myschool.adminservice.security;

import lombok.Data;

import java.util.List;

@Data
public class LoginResponse {
    private String token;
    private String tokenType = "Bearer";
    private String userName;
/*    private String email;
    List<String> roles;*/
    MyUserDetails myUserDetails;

    public LoginResponse(String token, String userName, MyUserDetails myUserDetails) {
        this.token = token;
        this.userName = userName;
        this.myUserDetails = myUserDetails;
    }
}
