package com.myschool.adminservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.myschool.adminservice.model.Course;
import com.myschool.adminservice.model.CourseWork;
import com.myschool.adminservice.model.SchoolMessage;
import com.myschool.adminservice.services.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/myschool")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "addcourse")
    public Course addCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return createdCourse;
    }

    @GetMapping(value = "allcourses")
    public List<Course> getAllCourses() {
        List<Course> courseList = courseService.getAllCourses();
        return courseList;
    }

    @GetMapping(value = "course/{id}")
    public Optional<Course> getCourseById(@PathVariable(value = "id") Integer id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course;
    }

    @GetMapping(value = "schoolcourses/{schoolId}")
    public List<Course> getCoursesBySchoolId(@PathVariable(value = "schoolId") long schoolId) {
        List<Course> courseList = courseService.getCoursesBySchoolId(schoolId);
        return courseList;
    }

    @PostMapping(value = "addcoursework")
    public CourseWork addCoursework(@RequestBody CourseWork courseWork) {
        CourseWork createdCoursework = courseService.createCourseWork(courseWork);
        return createdCoursework;
    }

    @PostMapping(value = "coursework/{id}/addfile")
    public CourseWork addCourseworkFile(@PathVariable(value = "id") long id,@RequestPart("uploadedFile") MultipartFile file) {
        CourseWork createdCoursework = courseService.addCourseWorkFile(id, file);
        return createdCoursework;
    }

    @GetMapping(value = "allcourseworks/{courseId}")
    public List<CourseWork> getCourseWorksByCourseId(@PathVariable(value = "courseId") long courseId) {
        List<CourseWork> courseWorkList = courseService.getCourseWorksByCourseId(courseId);
        return courseWorkList;
    }
}
