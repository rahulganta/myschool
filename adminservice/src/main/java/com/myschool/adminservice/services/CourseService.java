package com.myschool.adminservice.services;

import com.myschool.adminservice.exceptions.FileStorageException;
import com.myschool.adminservice.model.Course;
import com.myschool.adminservice.model.CourseWork;
import com.myschool.adminservice.model.SchoolMessage;
import com.myschool.adminservice.repository.CourseRepository;
import com.myschool.adminservice.repository.CourseWorkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseWorkRepository courseWorkRepository;

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

    public CourseWork createCourseWork(CourseWork courseWork) {
        LocalDateTime now = LocalDateTime.now();
        courseWork.setCreatedTimeStamp(now);

        CourseWork createdCourseWork = courseWorkRepository.save(courseWork);
        return createdCourseWork;
    }

    public CourseWork addCourseWorkFile(long id, MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            //add file name validations and restrictions

            /* TODO update only one file column by id, instead of updating entire row */
            CourseWork createdCourseWork = new CourseWork();
            Optional<CourseWork> optCourseWork = courseWorkRepository.findById(id);
            optCourseWork.orElseThrow().setUploadedFile(file.getBytes());
            optCourseWork.orElseThrow().setFileName(fileName);

            if (optCourseWork.isPresent()) {
                CourseWork courseWork1 = optCourseWork.get();
                createdCourseWork = courseWorkRepository.save(courseWork1);
            }
            return createdCourseWork;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public List<CourseWork> getCourseWorksByCourseId(long courseId) {
        Sort sort = Sort.by("createdTimeStamp").descending();
        List<CourseWork> courseWorks = courseWorkRepository.findAllByCourseId(courseId, sort);
        return courseWorks;
    }

}
