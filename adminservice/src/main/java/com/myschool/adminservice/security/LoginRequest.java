package com.myschool.adminservice.security;

import lombok.Data;

@Data
public class LoginRequest {
    //Add validation annotations
    private String username;
    private String password;
}
