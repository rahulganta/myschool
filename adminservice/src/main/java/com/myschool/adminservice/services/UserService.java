package com.myschool.adminservice.services;

import com.myschool.adminservice.model.User;
import com.myschool.adminservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        /*TODO do all the preliminary checks throw error*/
        User createdUser = userRepository.save(user);
        return createdUser;
    }
}
