package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

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

    @Column(columnDefinition="tinyint(1) default 1")
    private boolean active;

    private String roles;

    private long lastLogin;

    private String lang;

    @Column(columnDefinition = "varchar(255) default 'defaulttheme'")
    private String theme;

    @Column(columnDefinition = "BIGINT default 0")
    private long schoolId;

    @PrePersist
    void prePersist() {
        if (this.theme == null || this.theme.trim().isEmpty())
            this.theme = "defaulttheme";

        if (this.lang == null || this.lang.trim().isEmpty())
            this.lang = "en";
    }

}
