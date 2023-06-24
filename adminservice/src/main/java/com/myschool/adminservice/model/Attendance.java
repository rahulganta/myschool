package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "attendance")
@Slf4j
@JsonIgnoreProperties
public class Attendance {

    @EmbeddedId
    private AttendancePK attendancePK;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus attendanceStatus;

    private String comments;
}
