package com.myschool.adminservice.controller;

import com.myschool.adminservice.model.School;
import com.myschool.adminservice.model.User;
import com.myschool.adminservice.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/myschool")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "addadmin")
    @ResponseBody
    public User addSchool(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return createdUser;
    }
}
