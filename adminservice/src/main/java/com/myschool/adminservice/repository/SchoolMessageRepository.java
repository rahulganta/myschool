package com.myschool.adminservice.repository;

import com.myschool.adminservice.model.SchoolMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolMessageRepository extends JpaRepository<SchoolMessage, Long> {
    List<SchoolMessage>findAllBySchoolId(long schoolId);
}
