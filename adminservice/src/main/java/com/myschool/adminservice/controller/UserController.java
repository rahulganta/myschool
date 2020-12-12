package com.myschool.adminservice.controller;

import com.myschool.adminservice.model.User;
import com.myschool.adminservice.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/myschool")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "addadmin")
    @ResponseBody
    public User addAdmin(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return createdUser;
    }

    @GetMapping(value = "allusers")
    public List<User> getAllUsers() {
        List<User> usersList = userService.getAllUsers();
        return usersList;
    }

    @GetMapping(value = "alladmins")
    public List<User> getAllAdmins() {
        List<User> adminList = userService.getAllAdmins();
        return adminList;
    }

    @GetMapping(value = "user/{username}")
    public Optional<User> getUser(@PathVariable("username") String username) {
        Optional<User> user = userService.getUser(username);
        return user;
    }

    @GetMapping(value = "students/{schoolId}")
    public List<User> getStudentsBySchoolId(@PathVariable("schoolId") long schoolId) {
        List<User> studentsList = userService.getStudentsBySchoolId(schoolId);
        return studentsList;
    }

    @GetMapping(value = "teachers/{schoolId}")
    public List<User> getTeachersBySchoolId(@PathVariable("schoolId") long schoolId) {
        List<User> teachersList = userService.getTeachersBySchoolId(schoolId);
        return teachersList;
    }

    @GetMapping(value = "admins/{schoolId}")
    public List<User> getAdminsBySchoolId(@PathVariable("schoolId") long schoolId) {
        List<User> adminList = userService.getAdminsBySchoolId(schoolId);
        return adminList;
    }

    @GetMapping(value = "staff/{schoolId}")
    public List<User> getUsersByRolesAndSchoolId(@PathVariable("schoolId") long schoolId, @RequestParam String[] roles) {

        return (new ArrayList<User>());
    }
}
