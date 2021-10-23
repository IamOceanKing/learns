package com.oceanking.learn.spring;

import com.oceanking.learn.spring.springboot.bean.ConfigureBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

public class SpringApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.oceanking.learn.spring");
        ConfigureBean bean = ctx.getBean(ConfigureBean.class);
        System.out.println(bean);
    }
}
