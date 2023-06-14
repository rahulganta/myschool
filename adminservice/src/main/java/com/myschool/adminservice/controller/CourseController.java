package com.myschool.adminservice.controller;

import com.myschool.adminservice.model.*;
import com.myschool.adminservice.security.MyUserDetails;
import com.myschool.adminservice.services.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/myschool")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    ResourceBundleMessageSource messageSource;
   @GetMapping(value = "test")
    public String TestString() {
        Locale locale = LocaleContextHolder.getLocale();
        String welcome = messageSource.getMessage("welcome", null,new Locale("es"));
        return welcome;
    }

    @PreAuthorize("(#course.instructor == principal.username and hasRole('ROLE_TEACHER')) or hasAnyRole('ROLE_SUPERADMIN', 'ROLE_FRANCHISEADMIN', 'ROLE_SCHOOLADMIN')")
    @PostMapping(value = "addcourse")
    public Course addCourse(@RequestBody @Valid Course course) {
        Course createdCourse = courseService.createCourse(course);
        return createdCourse;
    }

    @GetMapping(value = "allcourses")
    public List<Course> getAllCourses() {
        List<Course> courseList = courseService.getAllCourses();
        return courseList;
    }

    @GetMapping(value = "course/{id}")
    public Course getCourseById(@PathVariable(value = "id") Integer id) {
        Course course = courseService.getCourseById(id);
        return course;
    }

    /**
     *
     * @param principal
     * @return
     */
    @GetMapping(value = "mycourses")
    public List<Course> getMyCourses(Principal principal) {
        String userName = principal.getName();
        Collection<GrantedAuthority> authorities = ((UsernamePasswordAuthenticationToken) principal).getAuthorities();
        List<Course> courseList = courseService.getMyCourses(userName);
        List<CourseRegistration> courseRegistrations = courseService.getCourseRegistrationsByStudentId(userName);
        List<Course> regCourseList = courseRegistrations.stream().map(CourseRegistration::getRegCourse).collect(Collectors.toList());
        courseList.addAll(regCourseList);
        return courseList;
    }

    @GetMapping(value = "myregisteredcourses")
    public List<Course> getMyRegisteredCourses(Principal principal) {
        String userName = principal.getName();
        Collection<GrantedAuthority> authorities = ((UsernamePasswordAuthenticationToken) principal).getAuthorities();
        List<CourseRegistration> courseRegistrations = courseService.getCourseRegistrationsByStudentId(userName);
        List<Course> regCourseList = courseRegistrations.stream().map(CourseRegistration::getRegCourse).collect(Collectors.toList());
        return regCourseList;
    }

    @GetMapping(value = "schoolcourses/{schoolId}")
    public List<Course> getCoursesBySchoolId(@PathVariable(value = "schoolId") long schoolId) {
        List<Course> courseList = courseService.getCoursesBySchoolId(schoolId);
        return courseList;
    }


    @PostMapping(value = "addcoursework")
    public CourseWork addCoursework(@RequestBody @Valid CourseWork courseWork) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        String username = myUserDetails.getUsername();
        Collection<GrantedAuthority> authorities = myUserDetails.getAuthorities();
        List<String> autorizedRles = new ArrayList<>(Arrays.asList("ROLE_SUPERADMIN", "ROLE_FRANCHISEADMIN", "ROLE_SCHOOLADMIN"));
        boolean hasRole = authorities.stream().anyMatch(element -> autorizedRles.contains(element.toString()));

        Course course = courseService.getCourseById(courseWork.getCourseId());
        if(course.getInstructor() == username || hasRole) {
            CourseWork createdCoursework = courseService.createCourseWork(courseWork);
            return createdCoursework;
        } else {
            throw new AccessDeniedException("Access denied to update or add coursework");
        }
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
    public List<CourseRegistration> addCourseRegistrations(@RequestBody List<@Valid CourseRegistration> courseRegistrations) {
        List<CourseRegistration> courseRegistrationList = courseService.addCourseRegistrations(courseRegistrations);
        return courseRegistrationList;
    }

    @PostMapping(value = "addstudenttocourse/{courseid}")
    public CourseRegistration addStudentToCourse(@PathVariable(value = "courseid") Integer courseId, @RequestBody @NotBlank String studentId) {
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
    public void deleteStudentFromCourse(@PathVariable(value = "courseid") @NotBlank Integer courseId, @RequestParam(value = "studentid") @NotBlank String studentId) {
        CourseRegistrationPK courseRegistrationPK = new CourseRegistrationPK(studentId, courseId);
        courseService.deleteCourseRegistrationById(courseRegistrationPK);
    }
}
