package com.myschool.adminservice.controller;

import com.myschool.adminservice.model.Attendance;
import com.myschool.adminservice.services.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/myschool")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class AttendanceController {
    @Autowired
    public AttendanceService attendanceService;

    @PostMapping(value = "/addattendance")
    public  List<Attendance> addAttendance(@RequestBody List<@Valid Attendance> attendanceList) {
        List<Attendance> attendances = attendanceService.addAttendance(attendanceList);
        return attendances;
    }

    @GetMapping("/attendance/course/{courseId}")
    public List<Attendance> getAttendanceByCourseId(@PathVariable Integer courseId) {
        //TODO: add optional params ie Year and Month
        List<Attendance> attendanceList = attendanceService.getAttendanceByCourseId(courseId);
        return attendanceList;
    }

    @GetMapping("/attendance/student/studentId")
    public List<Attendance> getAttendanceByStudentId(@PathVariable String studentId) {
        //TODO: add optional params ie Year and Month
        List<Attendance> attendanceList = attendanceService.getAttendanceByStudentId(studentId);
        return attendanceList;
    }
}
