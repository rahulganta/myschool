package com.myschool.adminservice.repository;

import com.myschool.adminservice.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}
