package com.myschool.adminservice.controller;

import com.myschool.adminservice.model.Course;
import com.myschool.adminservice.services.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
