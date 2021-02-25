package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties
@Embeddable
public class CourseRegistrationPK implements Serializable {

    @Column(name = "student_id", nullable = false)
    String studentId;

    @Column(name = "course_id", nullable = false)
    Integer courseId;

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.studentId.hashCode();
        hash = hash * prime + this.courseId.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            if (this == obj) return true;
            if (this == null) return false;
            if(getClass() != obj.getClass()) return false;
            CourseRegistrationPK that = (CourseRegistrationPK) obj;
            return studentId.equals(that.getStudentId()) && courseId.equals(that.getCourseId());
        } catch (Throwable ignored) {
            return false;
        }
    }
}

