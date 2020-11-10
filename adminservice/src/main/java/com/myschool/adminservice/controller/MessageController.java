package com.myschool.adminservice.controller;

import com.myschool.adminservice.model.Course;
import com.myschool.adminservice.model.CourseMessage;
import com.myschool.adminservice.model.SchoolMessage;
import com.myschool.adminservice.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/myschool/messages")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class MessageController {

    @Autowired
    private MessageService messageService;

    //CourseMessages Endpoints
    @PostMapping(value = "addcoursemessage")
    public CourseMessage addCourseMessage(@RequestBody CourseMessage courseMessage) {
        CourseMessage createdCourseMessage = messageService.createCourseMessage(courseMessage);
        return createdCourseMessage;
    }

    @GetMapping(value = "allcoursemessages")
    public List<CourseMessage> getAllCourseMessages() {
        List<CourseMessage> courseMessageList = messageService.getAllCourseMessages();
        return courseMessageList;
    }

    @GetMapping(value = "allcoursemessages/{courseId}")
    public List<CourseMessage> getMessagesByCourseId(@PathVariable(value = "courseId") long courseId) {
        List<CourseMessage> courseMessageList = messageService.getCourseMessagesByCourseId(courseId);
        return courseMessageList;
    }

    //SchoolMessages Endpoints
    @PostMapping(value = "addschoolmessage")
    public SchoolMessage addSchoolMessage(@RequestBody SchoolMessage schoolMessage) {
        SchoolMessage createdSchoolMessage = messageService.createSchoolMessage(schoolMessage);
        return createdSchoolMessage;
    }

    @GetMapping(value = "allschoolmessages")
    public List<SchoolMessage> getAllSchoolMessages() {
        List<SchoolMessage> schoolMessageList = messageService.getAllSchoolMessages();
        return schoolMessageList;
    }

    @GetMapping(value = "allschoolmessages/{schoolId}")
    public List<SchoolMessage> getMessagesBySchoolId(@PathVariable(value = "schoolId") long schoolId) {
        List<SchoolMessage> schoolMessageList = messageService.getSchoolMessagesBySchoolId(schoolId);
        return schoolMessageList;
    }
}
