package com.myschool.adminservice.repository;

import com.myschool.adminservice.model.CourseWork;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CourseWorkRepository extends PagingAndSortingRepository<CourseWork, Long> {
    List<CourseWork> findAllByCourseId(long courseId, Sort sort);
}
