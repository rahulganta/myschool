package com.myschool.adminservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.myschool.adminservice.model.*;
import com.myschool.adminservice.services.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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

    @PostMapping(value = "coursework/{id}/uploadfile")
    public CourseWork addCourseworkFile(@PathVariable(value = "id") long id, @RequestPart("uploadedFile") MultipartFile file) {
        CourseWork createdCoursework = courseService.addCourseWorkFile(id, file);
        return createdCoursework;
    }

    @GetMapping(value = "allcourseworks/{courseId}")
    public List<CourseWork> getCourseWorksByCourseId(@PathVariable(value = "courseId") Integer courseId) {
        List<CourseWork> courseWorkList = courseService.getCourseWorksByCourseId(courseId);
        return courseWorkList;
    }

    @GetMapping(value = "coursework/{id}/downloadfile")
    public ResponseEntity<byte[]> downloadCourseWorkFile(@PathVariable(value = "id") long id) throws Exception {
        Optional<CourseWork> optionalCourseWork = courseService.getCourseWorkById(id);
        if(!optionalCourseWork.isPresent()) {
            throw new Exception("Could not find document with ID: "+ id );
        }

        CourseWork courseWork = optionalCourseWork.get();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_OCTET_STREAM); //MediaType.valueOf(fileEntity.getContentType())
        header.setContentLength(courseWork.getUploadedFile().length);
        header.set("Content-Disposition", "attachment; filename=" + courseWork.getFileName());

        return new ResponseEntity<>(courseWork.getUploadedFile(), header, HttpStatus.OK);
    }

    @DeleteMapping("deletecoursework/{id}")
    public void deleteCourseWorkById(@PathVariable(value = "id")  long id) {
        courseService.deleteCourseWorkById(id);
    }

    @GetMapping(value = "courseregistrations")
    public List<CourseRegistration> getCourseRegistrationsByCourseId(@RequestParam(value = "courseid") Integer courseId) {
        List<CourseRegistration> courseRegistrationList = courseService.getCourseRegistrationsByCourseId(courseId);
        return courseRegistrationList;
    }

    @GetMapping(value = "student/{studentid}/courseregistrations")
    public List<CourseRegistration> getCourseRegistrationsByStudentId(@PathVariable(value = "studentid") String studentId) {
        List<CourseRegistration> courseRegistrationList = courseService.getCourseRegistrationsByStudentId(studentId);
        return courseRegistrationList;
    }

    @PostMapping(value = "addcourseregistrations")
    public List<CourseRegistration> addCourseRegistrations(@RequestBody List<CourseRegistration> courseRegistrations) {
        List<CourseRegistration> courseRegistrationList = courseService.addCourseRegistrations(courseRegistrations);
        return courseRegistrationList;
    }

    @PostMapping(value = "addstudenttocourse/{courseid}")
    public CourseRegistration addStudentToCourse(@PathVariable(value = "courseid") Integer courseId, @RequestBody String studentId) {
        if(studentId.contains("=")) {
            studentId = studentId.replaceAll("=$", "");
        }
        CourseRegistration courseRegistration = courseService.addStudentToCourse(studentId, courseId);
        return courseRegistration;
    }

    @DeleteMapping(value = "deletecourseregistration")
    public void deleteCourseRegistrationById(@RequestBody(required = false) CourseRegistrationPK courseRegistrationPK) {
        CourseRegistrationPK courseRegistrationPK1 = new CourseRegistrationPK();
        /*courseService.deleteCourseRegistrationById(courseRegistrationPK);*/
    }

    @DeleteMapping(value = "deletestudentfromcourse/{courseid}")
    public void deleteStudentFromCourse(@PathVariable(value = "courseid") Integer courseId, @RequestParam(value = "studentid") String studentId) {
        CourseRegistrationPK courseRegistrationPK = new CourseRegistrationPK(studentId, courseId);
        courseService.deleteCourseRegistrationById(courseRegistrationPK);
    }
}
