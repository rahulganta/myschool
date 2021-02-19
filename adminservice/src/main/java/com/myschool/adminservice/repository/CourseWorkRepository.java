package com.myschool.adminservice.repository;

import com.myschool.adminservice.model.CourseWork;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CourseWorkRepository extends PagingAndSortingRepository<CourseWork, Long> {
    //Return everything except uploadedfile
    @Query("SELECT new CourseWork(c.id, c.type, c.title, c.description, c.topic, c.createdTimeStamp, c.videoLink,c.fileName, c.fileSize, c.courseId) FROM CourseWork c where c.courseId = :courseId")
    List<CourseWork> findAllByCourseId(long courseId, Sort sort);
}
