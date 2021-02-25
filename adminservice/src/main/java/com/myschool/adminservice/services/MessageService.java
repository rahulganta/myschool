package com.myschool.adminservice.services;

import com.myschool.adminservice.model.CourseMessage;
import com.myschool.adminservice.model.SchoolMessage;
import com.myschool.adminservice.model.UserMessage;
import com.myschool.adminservice.repository.CourseMessageRepository;
import com.myschool.adminservice.repository.SchoolMessageRepository;
import com.myschool.adminservice.repository.UserMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class MessageService {

    @Autowired
    private CourseMessageRepository courseMessageRepository;

    @Autowired
    private SchoolMessageRepository schoolMessageRepository;

    @Autowired
    private UserMessageRepository userMessageRepository;

    //CourseMessages Methods
    public CourseMessage createCourseMessage(CourseMessage courseMessage) {
        /*TODO do all the preliminary checks throw error*/
        LocalDateTime now = LocalDateTime.now();
        courseMessage.setCreatedTimeStamp(now);
        CourseMessage createdCourseMessage = courseMessageRepository.save(courseMessage);
        return createdCourseMessage;
    }

    public List<CourseMessage> getAllCourseMessages() {
        List<CourseMessage> courseMessageList = courseMessageRepository.findAll();
        return courseMessageList;
    }

    public List<CourseMessage> getCourseMessagesByCourseId(Integer courseId) {
        List<CourseMessage> courseMessageList = courseMessageRepository.findAllByCourseId(courseId);
        return courseMessageList;
    }

    //SchoolMessages Methods
    public SchoolMessage createSchoolMessage(SchoolMessage schoolMessage) {
        LocalDateTime now = LocalDateTime.now();
        schoolMessage.setCreatedTimeStamp(now);
        SchoolMessage createdSchoolMessage = schoolMessageRepository.save(schoolMessage);
        return createdSchoolMessage;
    }

    public Iterable<SchoolMessage> getAllSchoolMessages() {
        Sort sort = Sort.by("createdTimeStamp").descending().and(Sort.by("priority"));
        Iterable<SchoolMessage> schoolMessageList = schoolMessageRepository.findAll(sort);
        return schoolMessageList;
    }

    public List<SchoolMessage> getSchoolMessagesBySchoolId(long schoolId) {
        Sort sort = Sort.by("createdTimeStamp").descending().and(Sort.by("priority"));
        List<SchoolMessage> schoolMessageList = schoolMessageRepository.findAllBySchoolId(schoolId, sort);
        return schoolMessageList;
    }

    //UserMessages Methods

    public UserMessage createUserMessage(UserMessage userMessage) {
        /*TODO do all the preliminary checks*/
        LocalDateTime now = LocalDateTime.now();
        userMessage.setCreatedTime(now);
        UserMessage resUserMessage = userMessageRepository.save(userMessage);
        return resUserMessage;
    }

    public List<UserMessage> getUserMessagesByUsername(String username) {
        List<UserMessage> userMessageList = userMessageRepository.findAllByPostedTo(username);
        return userMessageList;
    }
}
