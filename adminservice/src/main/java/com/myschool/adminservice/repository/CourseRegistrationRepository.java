package com.myschool.adminservice.repository;

import com.myschool.adminservice.model.CourseRegistration;
import com.myschool.adminservice.model.CourseRegistrationPK;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CourseRegistrationRepository extends PagingAndSortingRepository<CourseRegistration, CourseRegistrationPK> {
    List<CourseRegistration> findCourseRegistrationsByRegCourse_Id(Integer courseId, Sort sort);
    List<CourseRegistration> findCourseRegistrationsByStudent_Username(String username, Sort sort);
}
