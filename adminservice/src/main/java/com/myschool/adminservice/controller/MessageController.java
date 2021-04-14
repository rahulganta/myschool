package com.myschool.adminservice.controller;

import com.myschool.adminservice.model.CourseMessage;
import com.myschool.adminservice.model.SchoolMessage;
import com.myschool.adminservice.model.UserMessage;
import com.myschool.adminservice.security.MyUserDetails;
import com.myschool.adminservice.services.MessageService;
import com.myschool.adminservice.util.AuthenticationFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/myschool/messages")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    //CourseMessages Endpoints
    @PostMapping(value = "addcoursemessage")
    public CourseMessage addCourseMessage(@RequestBody CourseMessage courseMessage) {
        MyUserDetails myUserDetails = authenticationFacade.getLoginUserDetails();
        courseMessage.setPostedBy(myUserDetails.getUsername());

        CourseMessage createdCourseMessage = messageService.createCourseMessage(courseMessage);
        return createdCourseMessage;
    }

    @GetMapping(value = "allcoursemessages")
    public List<CourseMessage> getAllCourseMessages() {
        List<CourseMessage> courseMessageList = messageService.getAllCourseMessages();
        return courseMessageList;
    }

    @GetMapping(value = "allcoursemessages/{courseId}")
    public List<CourseMessage> getMessagesByCourseId(@PathVariable(value = "courseId") Integer courseId) {
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
    public Iterable<SchoolMessage> getAllSchoolMessages() {
        Iterable<SchoolMessage> schoolMessageList = messageService.getAllSchoolMessages();
        return schoolMessageList;
    }

    @GetMapping(value = "allschoolmessages/{schoolId}")
    public List<SchoolMessage> getMessagesBySchoolId(@PathVariable(value = "schoolId") long schoolId) {
        List<SchoolMessage> schoolMessageList = messageService.getSchoolMessagesBySchoolId(schoolId);
        return schoolMessageList;
    }

    //UserMessage Endpoints
    @PostMapping(value = "addusermessage")
    public UserMessage addUserMessage(@RequestBody UserMessage userMessage) {
        MyUserDetails myUserDetails = authenticationFacade.getLoginUserDetails();
        userMessage.setPostedBy(myUserDetails.getUsername());
        UserMessage resUserMessage = messageService.createUserMessage(userMessage);
        return resUserMessage;
    }

    @GetMapping(value = "usermessages")
    public List<UserMessage> getUserMessages(@RequestParam(name = "username", required = false) String username) {
        if(username == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
            username = myUserDetails.getUsername();
        }
        List<UserMessage> userMessageList = messageService.getUserMessagesByUsername(username);
        return userMessageList;
    }
}
