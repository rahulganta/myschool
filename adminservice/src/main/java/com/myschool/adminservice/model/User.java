package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties
@Entity
@Table(name="users")
public class User {

    @Id
    @Column(unique=true)
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    @Column(columnDefinition="boolean default true")
    private boolean active;

    private String roles;

    private long lastLogin;

    private String lang;

    @Column(columnDefinition = "varchar(255) default 'defaulttheme'")
    private String theme;

    @Column(columnDefinition = "BIGINT default 1")
    private long schoolId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="schoolId", referencedColumnName = "id", updatable=false, insertable = false)
    private School school;

    @PrePersist
    void prePersist() {
        if (this.theme == null || this.theme.trim().isEmpty())
            this.theme = "defaulttheme";

        if (this.lang == null || this.lang.trim().isEmpty())
            this.lang = "en";
    }

}
