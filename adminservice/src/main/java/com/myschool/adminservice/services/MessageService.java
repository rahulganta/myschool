package com.myschool.adminservice.services;

import com.myschool.adminservice.model.CourseMessage;
import com.myschool.adminservice.model.SchoolMessage;
import com.myschool.adminservice.repository.CourseMessageRepository;
import com.myschool.adminservice.repository.SchoolMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    //CourseMessages Methods
    public CourseMessage createCourseMessage(CourseMessage courseMessage) {
        /*TODO do all the preliminary checks throw error*/
        CourseMessage createdCourseMessage = courseMessageRepository.save(courseMessage);
        return createdCourseMessage;
    }

    public List<CourseMessage> getAllCourseMessages() {
        List<CourseMessage> courseMessageList = courseMessageRepository.findAll();
        return courseMessageList;
    }

    public List<CourseMessage> getCourseMessagesByCourseId(long courseId) {
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

    public List<SchoolMessage> getAllSchoolMessages() {
        List<SchoolMessage> schoolMessageList = schoolMessageRepository.findAll();
        return schoolMessageList;
    }

    public List<SchoolMessage> getSchoolMessagesBySchoolId(long schoolId) {
        List<SchoolMessage> schoolMessageList = schoolMessageRepository.findAllBySchoolId(schoolId);
        return schoolMessageList;
    }

    //UserMessages Methods
}
