package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    String name;

    String displayName;
    String franchiseName;
    String correspondent;

    @Column(columnDefinition = "varchar(255) default 'Active'")
    String status;

    @OneToMany(mappedBy = "courseSchool", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Course> courses = new ArrayList<>();

    @PrePersist
    void prePersist() {
        if (this.status == null || this.status.trim().isEmpty())
            this.status = "Active";
    }
}
