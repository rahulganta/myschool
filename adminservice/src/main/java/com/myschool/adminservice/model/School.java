package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "school")
@JsonIgnoreProperties
public class School {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "School name cannot be blank")
    @Size(min = 3, max = 30, message = "School name must be between 3 and 30 characters")
    private String name;

    @NotBlank(message = "School DisplayName cannot be blank")
    @Size(min = 3, max = 30, message = "School displayname must be between 3 and 30 characters")
    String displayName;

    String franchiseName;

    String correspondent;

    @Column(columnDefinition = "varchar(255) default 'Active'")
    String status;

    /*@OneToMany(mappedBy = "courseSchool", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();*/

    @PrePersist
    void prePersist() {
        if (this.status == null || this.status.trim().isEmpty())
            this.status = "Active";
    }
}
