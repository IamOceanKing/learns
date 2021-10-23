package com.oceanking.learn.spring.springboot.service;

import com.oceanking.learn.spring.springboot.bean.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class MyService {
    @Cacheable(value = "user")
    public User getCacheableUser() {
        return User.builder().username(UUID.randomUUID().toString()).build();
    }
}
