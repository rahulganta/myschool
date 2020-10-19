package com.myschool.adminservice.services;

import com.myschool.adminservice.model.Course;
import com.myschool.adminservice.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course) {
        /*TODO do all the preliminary checks throw error*/
        Course createdCourse = courseRepository.save(course);
        return createdCourse;
    }

    public List<Course> getAllCourses() {
        List<Course> courseList = courseRepository.findAll();
        return courseList;
    }

    public Optional<Course> getCourseById(Integer id) {
        Optional<Course> courseList = courseRepository.findById(id);
        return courseList;
    }

    public List<Course> getCoursesBySchoolId(long schoolId) {
        List<Course> courseList = courseRepository.findAllByCourseSchoolId(schoolId);
        return courseList;
    }
}
