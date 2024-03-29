package com.myschool.adminservice.services;

import com.myschool.adminservice.exceptions.FileStorageException;
import com.myschool.adminservice.model.*;
import com.myschool.adminservice.repository.CourseRegistrationRepository;
import com.myschool.adminservice.repository.CourseRepository;
import com.myschool.adminservice.repository.CourseWorkRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
@Slf4j
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseWorkRepository courseWorkRepository;

    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;

    public Course createCourse(Course course) {
        /*TODO do all the preliminary checks throw error*/
        Course createdCourse = courseRepository.save(course);
        return createdCourse;
    }

    public List<Course> getAllCourses() {
        List<Course> courseList = courseRepository.findAll();
        return courseList;
    }

    public Course getCourseById(Integer id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isPresent()) {
            return optionalCourse.get();
        } else {
            return new Course();
        }
    }

    public List<Course> getCoursesBySchoolId(long schoolId) {
        List<Course> courseList = courseRepository.findAllByCourseSchoolId(schoolId);
        return courseList;
    }
    public List<Course> getMyCourses(String instructor) {
        List<Course> courseList = courseRepository.findAllByInstructor(instructor);
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
        long fileSize = file.getSize();
        try {
            //add file name and file size validations and restrictions

            /* TODO update only one file column by id, instead of updating entire row */
            CourseWork createdCourseWork = new CourseWork();
            Optional<CourseWork> optCourseWork = courseWorkRepository.findById(id);
            if (optCourseWork.isPresent()) {
                CourseWork courseWork = optCourseWork.get();
                courseWork.setUploadedFile(file.getBytes());
                courseWork.setFileName(fileName);
                courseWork.setFileSize(fileSize);
                createdCourseWork = courseWorkRepository.save(courseWork);
            }
            return createdCourseWork;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public List<CourseWork> getCourseWorksByCourseId(Integer courseId) {
        Sort sort = Sort.by("createdTimeStamp").descending();
        List<CourseWork> courseWorks = courseWorkRepository.findAllByCourseId(courseId, sort);
        return courseWorks;
    }

    public Optional<CourseWork> getCourseWorkById(long id) {
       Optional<CourseWork> courseWork = courseWorkRepository.findById(id);
       return courseWork;
    }

    public void deleteCourseWorkById(long id) {
        courseWorkRepository.deleteById(id);
    }

    public List<CourseRegistration> getCourseRegistrationsByCourseId(Integer courseId) {
        Sort sort = Sort.by("student.username").descending();
        List<CourseRegistration> courseRegistrationList = courseRegistrationRepository.findCourseRegistrationsByRegCourse_Id(courseId, sort);
        return courseRegistrationList;
    }

    public List<CourseRegistration> getCourseRegistrationsByStudentId(String studentId) {
        Sort sort = Sort.by("student.username").descending();
        List<CourseRegistration> courseRegistrationList = courseRegistrationRepository.findCourseRegistrationsByStudent_Username(studentId, sort);
        return courseRegistrationList;
    }


    public List<CourseRegistration> addCourseRegistrations(List<CourseRegistration> courseRegistrations) {
        List<CourseRegistration> courseRegistrationList = null;
        try {
            courseRegistrationList = (List<CourseRegistration>) courseRegistrationRepository.saveAll(courseRegistrations);
        } catch (DataIntegrityViolationException ex)  {
            throw new DataIntegrityViolationException(ex.getMostSpecificCause().getLocalizedMessage(), ex);
        }
        return courseRegistrationList;
    }

    public CourseRegistration addStudentToCourse(String studentId, Integer courseId)  {
        CourseRegistration createdCourseRegistration = null;
        try {
            CourseRegistrationPK courseRegistrationPK = new CourseRegistrationPK(studentId,courseId);
            LocalDateTime currentTime = LocalDateTime.now();
            CourseRegistration courseRegistration = new CourseRegistration(courseRegistrationPK , currentTime,  0);
            createdCourseRegistration = courseRegistrationRepository.save(courseRegistration);

        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException(ex.getMostSpecificCause().getLocalizedMessage(), ex);
        }
        return createdCourseRegistration;
    }

    public void deleteCourseRegistrationById(CourseRegistrationPK courseRegistrationPK) {
        courseRegistrationRepository.deleteById(courseRegistrationPK);
    }

    public void deleteCourseRegistrations(List<CourseRegistration> courseRegistrations) {
        courseRegistrationRepository.deleteAll(courseRegistrations);
    }
}
