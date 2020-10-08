package com.myschool.adminservice.services;

import com.myschool.adminservice.model.School;
import com.myschool.adminservice.repository.SchoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;

    public School createSchool(School school) {
        /*TODO do all the preliminary checks throw error*/
            School createdSchool = schoolRepository.save(school);
            return createdSchool;
    }
}
