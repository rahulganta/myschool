package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@JsonIgnoreProperties
public class AttendancePK implements Serializable {

    private static final long serialVersionUID = 9007688377090718299L;
    @NotBlank
    @Column(name = "student_id", nullable = false)
    private String studentId;

    @NotBlank
    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @ApiModelProperty(dataType = "java.lang.String", example = "2023-06-18")
    @PastOrPresent
    @Column(name = "attendance_date", nullable = false)
    private LocalDate attendanceDate;

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.studentId.hashCode();
        hash = hash * prime + this.courseId.hashCode();
        hash = hash * prime + this.attendanceDate.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            if (this == obj) return true;
            if (this == null) return false;
            if(getClass() != obj.getClass()) return false;
            AttendancePK that = (AttendancePK) obj;
            return studentId.equals(that.getStudentId()) && courseId.equals(that.getCourseId()) && attendanceDate.equals(that.getAttendanceDate());
        } catch (Throwable ignored) {
            return false;
        }
    }
}
