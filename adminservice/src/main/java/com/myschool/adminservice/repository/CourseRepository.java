package com.myschool.adminservice.repository;

import com.myschool.adminservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findAllByCourseSchoolId(long courseSchoolId);
}
