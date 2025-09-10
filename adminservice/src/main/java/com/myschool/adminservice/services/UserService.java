package com.myschool.adminservice.services;

import com.myschool.adminservice.exceptions.EntityAlreadyExistsException;
import com.myschool.adminservice.model.User;
import com.myschool.adminservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.Boolean.TRUE;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user){
        /*TODO do all the preliminary checks throw error*/
        if(!user.isActive()) {
            user.setActive(TRUE);
        }
        if(getUser(user.getUsername()).isPresent()) {
            Object[] args = {user.getUsername()};
            throw new EntityAlreadyExistsException("user.alreadyexists", "User is already present in the system.", "Err_UserExists", args);
        }
        User createdUser = userRepository.save(user);
        return createdUser;
    }

    public User updateUser(User user) throws Exception {
        /*TODO do all the preliminary checks throw error*/
        if(!user.isActive()) {
            user.setActive(TRUE);
        }
        if(getUser(user.getUsername()).isPresent()) {
            User createdUser = userRepository.save(user);
            return createdUser;
        } else {
            throw new RuntimeException("user.notfound");
        }

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

    public List<User> getUsersBySchoolIdAndRoles(long schoolId, List<String> roles) {
        List<User> userList = userRepository.findUsersBySchoolIdandRoles(schoolId, roles);
        return userList;
    }

    public List<User> searchStudents(String keyword, long schoolId) {
        List<User> studentList = userRepository.searchStudents(keyword, schoolId);
        return studentList;
    }

}
