package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "attendance")
@Slf4j
@JsonIgnoreProperties
public class Attendance {

    @EmbeddedId
    private AttendancePK attendancePK;

    @Column(columnDefinition="boolean default FALSE")
    private boolean attended;
}
