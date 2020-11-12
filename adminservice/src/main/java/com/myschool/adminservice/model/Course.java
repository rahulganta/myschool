package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String description;

    private String grade;

    private String instructor;

    private String createdBy;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    @Column(columnDefinition = "BIGINT default 0")
    private long courseSchoolId;

    @ManyToOne
    @JoinColumn(name="courseSchoolId", referencedColumnName = "id", updatable=false, insertable = false)
    @JsonBackReference
    private School courseSchool;

}
