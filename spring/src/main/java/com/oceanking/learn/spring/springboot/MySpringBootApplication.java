package com.oceanking.learn.spring.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MySpringBootApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(MySpringBootApplication.class, args);
    }
}
