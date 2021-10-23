package com.oceanking.learn.spring.springboot.event;

import com.oceanking.learn.spring.springboot.bean.User;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {
    private User user;

    public MyEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
