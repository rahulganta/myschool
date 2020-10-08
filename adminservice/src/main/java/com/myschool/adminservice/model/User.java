package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
@JsonIgnoreProperties
public class User {

    @Id
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private boolean active;

    private String roles;

    private long lastLogin;

    private String lang;

    @Column(columnDefinition = "varchar(255) default 'defaulttheme'", nullable = false)
    private String theme;

}
