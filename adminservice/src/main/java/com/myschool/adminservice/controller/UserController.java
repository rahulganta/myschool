package com.myschool.adminservice.controller;

import com.myschool.adminservice.model.User;
import com.myschool.adminservice.services.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    //TODO: Method name and url need to be changed as it can be used to add any user instead of just admin
    @PostMapping(value = "addadmin")
    @ResponseBody
    @PreAuthorize("#user.schoolId == principal.school.id or hasAnyRole('ROLE_SUPERADMIN', 'ROLE_FRANCHISEADMIN', 'ROLE_SCHOOLADMIN')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = false, allowEmptyValue = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token"),
            @ApiImplicitParam(name = "Accept-language", value = "language", required = false, allowEmptyValue = true, paramType = "header", dataTypeClass = String.class, example = "en_US")
    })
    public User addAdmin(@P("user")@Valid @RequestBody User user) {
        //((MyUserDetails) authentication.principal).school.id
        User createdUser = userService.createUser(user);
        return createdUser;
    }

    @SneakyThrows
    @PutMapping(value = "updateuser")
    @ResponseBody
    @PreAuthorize("#user.schoolId == principal.school.id or hasAnyRole('ROLE_SUPERADMIN', 'ROLE_FRANCHISEADMIN', 'ROLE_SCHOOLADMIN')")
    public User updateUser(@P("user")@Valid @RequestBody User user) {
        //((MyUserDetails) authentication.principal).school.id
        User createdUser = null;
        try {
            createdUser = userService.updateUser(user);
        } catch (Exception exception) {
            log.error(exception.getLocalizedMessage(), exception);
            throw new Exception(exception.getMessage(), exception);
        }
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

    @GetMapping(value = "users/{schoolId}")
    public List<User> getUsersBySchoolIdAndRoles(@PathVariable("schoolId") long schoolId, @RequestParam(name = "roles", required = false) List<String> roles) {
            List<User> userList = userService.getUsersBySchoolIdAndRoles(schoolId, roles);
            return userList;
    }
    @GetMapping(value = "searchstudents/{schoolId}")
    public List<User> searchStudents(@RequestParam(value = "q") String searchWord, @PathVariable(value = "schoolId") long schoolId) {
        List<User> studentsList = userService.searchStudents(searchWord, schoolId);
        return studentsList;
    }
}
