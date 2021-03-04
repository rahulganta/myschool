package com.myschool.adminservice.repository;

import com.myschool.adminservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT n FROM User n where n.roles in ('ROLE_SCHOOLADMIN','ROLE_FRANCHISEADMIN')")
    List<User> findAllAdmin();

    @Query("SELECT n FROM User n where n.roles = 'ROLE_TEACHER' and n.schoolId = :schoolId")
    List<User> findTeachersBySchoolId(long schoolId);

    @Query("SELECT n FROM User n where n.roles = 'ROLE_SCHOOLADMIN' and n.schoolId = :schoolId")
    List<User> findAdminsBySchoolId(long schoolId);

    @Query("SELECT n FROM User n where n.roles = 'ROLE_STUDENT' and n.schoolId = :schoolId")
    List<User> findStudentsBySchoolId(long schoolId);

    @Query("SELECT n FROM User n where n.schoolId = :schoolId and n.roles in :roles")
    List<User> findUsersBySchoolIdandRoles(long schoolId, List<String> roles);

    @Query("SELECT n FROM User n WHERE CONCAT(n.firstName, n.lastName, n.username, n.email) LIKE %:keyword% and n.schoolId = :schoolId and n.roles = 'ROLE_STUDENT'")
    List<User> searchStudents(String keyword, long schoolId);
}
