package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String firstName;

    private String lastName;

    private String email;

    @Column(columnDefinition="boolean default TRUE")
    private boolean active;

    private String roles;

    private LocalDateTime lastLogin;

    private String lang;

    @Column(columnDefinition = "varchar(255) default 'defaulttheme'")
    private String theme;

    @Column(columnDefinition = "BIGINT default 1")
    private long schoolId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="schoolId", referencedColumnName = "id", updatable=false, insertable = false)
    private School school;

    /*@OneToMany(mappedBy = "student")
    @JsonManagedReference
    Set<CourseRegistration> courseRegistrations;*/

    @PrePersist
    void prePersist() {
        if (this.theme == null || this.theme.trim().isEmpty())
            this.theme = "defaulttheme";

        if (this.lang == null || this.lang.trim().isEmpty())
            this.lang = "en";
    }

}
