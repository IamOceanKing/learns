package com.oceanking.learn.spring.springboot.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MySpringApplicationEventListener implements ApplicationListener<SpringApplicationEvent> {

    @Override
    public void onApplicationEvent(SpringApplicationEvent event) {
        SpringApplication springApplication = event.getSpringApplication();
        System.out.println(springApplication);
    }
}
