package com.oceanking.learn.spring.springboot.aware;

import com.oceanking.learn.spring.springboot.bean.ConfigureBean;
import com.oceanking.learn.spring.springboot.bean.MyBean;
import com.oceanking.learn.spring.springboot.ioc.MyIOCBean;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
public class AppContextHolder implements ApplicationContextAware, SmartLifecycle {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MyBean bean = applicationContext.getBean(MyBean.class);
        MyIOCBean bean2 = applicationContext.getBean(MyIOCBean.class);
        ConfigureBean configureBean = applicationContext.getBean(ConfigureBean.class);
        this.applicationContext = applicationContext;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}