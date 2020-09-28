package com.myschool.adminservice.controller;

import com.myschool.adminservice.model.School;
import com.myschool.adminservice.services.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/myschool")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @PostMapping(value = "addschool")
    @ResponseBody
    public School addSchool(@RequestBody School school) {
        School createdSchool = schoolService.createSchool(school);
        return createdSchool;
    }
}
