package com.myschool.adminservice.services;

import com.myschool.adminservice.model.School;
import com.myschool.adminservice.repository.SchoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<School> getAllSchools(){
        List<School> schoolList = schoolRepository.findAll();
        return schoolList;
    }

    public Optional<School> getSchool(Long schoolId) {
        Optional<School> school = schoolRepository.findById(schoolId);
        return school;
    }
}
