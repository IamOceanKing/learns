package com.oceanking.learn.spring.springboot.controller;

import com.oceanking.learn.spring.springboot.aware.AppContextHolder;
import com.oceanking.learn.spring.springboot.bean.MyBean;
import com.oceanking.learn.spring.springboot.bean.User;
import com.oceanking.learn.spring.springboot.event.MyEvent;
import com.oceanking.learn.spring.springboot.service.MyService;
import com.oceanking.learn.spring.springboot.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/learn/springboot")
public class MyController {
    @Resource
    private MyService myService;
    @Resource
    private UserService userService;


    /**
     * Accept表示浏览器期望请求得到资源类型
     * Content-Type表示服务器返回资源类型
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/cache")
    public User testCache(HttpServletRequest request) {
        return myService.getCacheableUser();
    }

    @RequestMapping(value = "/event")
    public String testEvent(HttpServletRequest request) {
        AppContextHolder.applicationContext.publishEvent(new MyEvent(this, User.builder().username("haha").build()));
        return "OK";
    }
}
