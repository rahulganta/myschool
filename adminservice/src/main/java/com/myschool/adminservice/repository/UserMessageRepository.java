package com.myschool.adminservice.repository;

import com.myschool.adminservice.model.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {
    List<UserMessage> findAllByPostedTo(String username);
}
