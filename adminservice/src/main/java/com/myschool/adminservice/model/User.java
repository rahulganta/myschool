package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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
    @NotBlank(message = "field.notblank")
    @Size(min = 5, max = 30, message = "field.size")
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotBlank(message = "field.notblank")
    private String firstName;

    @NotBlank(message = "field.notblank")
    private String lastName;

    private String email;

    @Column(columnDefinition="boolean default TRUE")
    private boolean active;

    @NotBlank(message = "Role cannot be blank")
    private String roles;

    @ApiModelProperty(dataType = "java.lang.String", example = "2019-01-23T17:09:42.411")
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
