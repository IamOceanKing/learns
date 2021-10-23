package com.oceanking.learn.spring.springboot.service;

import com.oceanking.learn.spring.springboot.bean.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserService {
    public User getUser() {
        return User.builder().username(UUID.randomUUID().toString()).build();
    }
}
