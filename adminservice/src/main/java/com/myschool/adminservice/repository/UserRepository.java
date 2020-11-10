package com.myschool.adminservice.repository;

import com.myschool.adminservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT n FROM User n where n.roles in ('ROLE_SCHOOLADMIN','ROLE_FRANCHISEADMIN')")
    List<User> findAllAdmin();
}
