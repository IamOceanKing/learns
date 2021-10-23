package com.oceanking.learn.spring.springboot.bean;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class ConfigureBean {
    @Bean
    public MyBean generateBean() {
        return new MyBean();
    }
}
