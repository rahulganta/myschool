package com.myschool.adminservice.services;

import com.myschool.adminservice.model.User;
import com.myschool.adminservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.TRUE;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        /*TODO do all the preliminary checks throw error*/
        if(!user.isActive()) {
            user.setActive(TRUE);
        }
        User createdUser = userRepository.save(user);
        return createdUser;
    }

    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public List<User> getAllAdmins() {
        List<User> adminList = userRepository.findAllAdmin();
        return adminList;
    }

    public Optional<User> getUser(String username) {
        Optional<User> user = userRepository.findById(username);
        return user;
    }

    public List<User> getTeachersBySchoolId(long schoolId) {
        List<User> teacherList = userRepository.findTeachersBySchoolId(schoolId);
        return teacherList;
    }

    public List<User> getAdminsBySchoolId(long schoolId) {
        List<User> adminList = userRepository.findAdminsBySchoolId(schoolId);
        return adminList;
    }

    public List<User> getStudentsBySchoolId(long schoolId) {
        List<User> studentList = userRepository.findStudentsBySchoolId(schoolId);
        return studentList;
    }

}
