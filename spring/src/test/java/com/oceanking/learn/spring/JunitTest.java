package com.oceanking.learn.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringApplication.class)
public class JunitTest {
    @Resource
    private com.oceanking.learn.spring.springboot.aware.AppContextHolder AppContextHolder;

    @Test
    public void test1() {
        System.out.println(AppContextHolder);
    }
}
