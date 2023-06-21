package com.myschool.adminservice.repository;

import com.myschool.adminservice.model.Attendance;
import com.myschool.adminservice.model.AttendancePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, AttendancePK> {
    List<Attendance> findAttendancesByAttendancePK_CourseId(Integer courseId);
    List<Attendance> findAttendancesByAttendancePK_StudentId(String studentId);
}
