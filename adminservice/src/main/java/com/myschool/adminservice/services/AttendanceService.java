package com.myschool.adminservice.services;

import com.myschool.adminservice.model.Attendance;
import com.myschool.adminservice.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;
    public List<Attendance> addAttendance(List<Attendance> attendanceList) {
        //Custome code
        List<Attendance> attendances = attendanceRepository.saveAll(attendanceList);
        return attendances;
    }

    public List<Attendance> getAttendanceByCourseId(Integer courseId) {
       return attendanceRepository.findAttendancesByAttendancePK_CourseId(courseId);
    }

    public List<Attendance> getAttendanceByStudentId(String studentId) {
        return attendanceRepository.findAttendancesByAttendancePK_StudentId(studentId);
    }
}
