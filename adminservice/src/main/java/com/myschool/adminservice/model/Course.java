package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties
@Entity
@Table(name = "course")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String description;

    private String grade;

    private String instructor;

    private String createdBy;

    @ApiModelProperty(dataType = "java.lang.String", example = "")
    private LocalDate startDate;

    @ApiModelProperty(dataType = "java.lang.String", example = "")
    private LocalDate endDate;

    private String status;

    @Column(columnDefinition = "BIGINT default 0")
    private long courseSchoolId;

    @ManyToOne
    @JoinColumn(name = "courseSchoolId", referencedColumnName = "id", updatable=false, insertable = false)
    @JsonBackReference
    private School courseSchool;

   /* @OneToMany(mappedBy = "mesCourse")
    @JsonManagedReference
    Set<CourseMessage> courseMessages;*/

    /*@OneToMany(mappedBy = "regCourse")
    @JsonManagedReference
    Set<CourseRegistration> courseRegistrations;*/

}
