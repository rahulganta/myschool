package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties
@Entity
@Table(name = "course_registraion")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CourseRegistration implements Serializable {
    @EmbeddedId
    private CourseRegistrationPK courseRegistrationPK;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("studentId")
    @JoinColumn(name = "studentId")
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId")
    @JoinColumn(name = "courseId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Course regCourse;

    @ApiModelProperty(dataType = "java.lang.String", example = "")
    private LocalDateTime registeredDate;

    private int grade;
}
