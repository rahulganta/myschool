package com.myschool.adminservice.controller;

import com.myschool.adminservice.model.School;
import com.myschool.adminservice.services.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/myschool")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @PostMapping(value = "addschool")
    public School addSchool(@Valid @RequestBody School school) {
        School createdSchool = schoolService.createSchool(school);
        return createdSchool;
    }

    @GetMapping(value = "allschools")
    /*@RolesAllowed({ "ROLE_SUPERADMIN", "ROLE_FRANCHISEADMIN" })*/
    /*@PreAuthorize("hasAnyRole('ROLE_SUPERADMIN', 'ROLE_FRANCHISEADMIN')")*/
    public List<School> getAllSchools() {
        //TODO Filter the results if the users role is ROLE_FRANCHISEADMIN
        List<School> schoolList = schoolService.getAllSchools();
        return schoolList;
    }

    @GetMapping(value = "school/{schoolId}")
    public Optional<School> getSchool(@PathVariable("schoolId") Long id) {
        Optional<School> School = schoolService.getSchool(id);
        return School;
    }

}
