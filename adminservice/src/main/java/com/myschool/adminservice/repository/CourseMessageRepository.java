package com.myschool.adminservice.repository;

import com.myschool.adminservice.model.CourseMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseMessageRepository extends JpaRepository<CourseMessage, Long> {
    List<CourseMessage> findAllByCourseId(long courseId);
}
