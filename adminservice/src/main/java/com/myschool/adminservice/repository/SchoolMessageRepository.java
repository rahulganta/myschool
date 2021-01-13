package com.myschool.adminservice.repository;

import com.myschool.adminservice.model.SchoolMessage;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SchoolMessageRepository extends PagingAndSortingRepository<SchoolMessage, Long> {
    List<SchoolMessage>findAllBySchoolIdOrderByCreatedTimeStampDesc(long schoolId);
    List<SchoolMessage>findAllBySchoolId(long schoolId, Sort sort);
}
