package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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

    @NotBlank(message = "field.notblank")
    private String name;

    private String description;

    @Size(min = 1, max = 10, message = "field.size")
    private String grade;

    private String instructor;

    private String createdBy;

    @ApiModelProperty(dataType = "java.lang.String", example = "2019-01-23")
    private LocalDate startDate;

    @ApiModelProperty(dataType = "java.lang.String", example = "2019-01-23")
    private LocalDate endDate;

    private String status;

    @Column(columnDefinition = "BIGINT default 0")
    private long courseSchoolId;

    @ApiModelProperty(hidden = true)
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "courseSchoolId", referencedColumnName = "id", updatable = false, insertable = false)
    @JsonBackReference
    private School courseSchool;

   /* @OneToMany(mappedBy = "mesCourse")
    @JsonManagedReference
    Set<CourseMessage> courseMessages;*/

    /*@OneToMany(mappedBy = "regCourse")
    @JsonManagedReference
    Set<CourseRegistration> courseRegistrations;*/
}
