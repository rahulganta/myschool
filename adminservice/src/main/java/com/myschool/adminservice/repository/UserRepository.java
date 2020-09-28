package com.myschool.adminservice.repository;

import com.myschool.adminservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
