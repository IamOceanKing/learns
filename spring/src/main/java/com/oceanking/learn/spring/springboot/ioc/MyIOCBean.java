package com.oceanking.learn.spring.springboot.ioc;

import com.oceanking.learn.spring.springboot.bean.MyBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.LifecycleProcessor;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * 执行顺序：
 * 1.实例化对象：构造函数 初始化...。对于BeanFactory容器，当客户向容器请求一个尚未初始化的bean时，或初始化bean的时候需要注入另一个尚未初始化的依赖时，容器就会调用createBean进行实例化。对于ApplicationContext容器，当容器启动结束后，便实例化所有的bean。
 * 2.设置属性：依赖注入！！！实例化后的对象被封装在BeanWrapper对象中，并且此时对象仍然是一个原生的状态，并没有进行依赖注入。紧接着，Spring根据BeanDefinition中的信息进行依赖注入。并且通过BeanWrapper提供的设置属性的接口完成依赖注入。
 * 3.检查ware相关接口：BeanNameAware...myIOCBean
 * 3.检查ware相关接口：BeanFactoryAware...com.oceanking.learn.spring.springboot.ioc.MyIOCBean@7c9bdee9
 * 4.BeanPostProcessor：postProcessBeforeInitialization...myIOCBean=>com.oceanking.learn.spring.springboot.ioc.MyIOCBean@7c9bdee9
 * 5.init方法：PostConstruct...
 * 6.InitializingBean方法：afterPropertiesSet...
 * 7.BeanPostProcessor：postProcessAfterInitialization...myIOCBean=>com.oceanking.learn.spring.springboot.ioc.MyIOCBean@7c9bdee9
 * 8.DisposableBean：销毁方法
 * <p>
 * 请注意Spring容器保证在bean的所有依赖都满足后立即执行配置的初始化回调。
 * 这意味着初始化回调在原生bean上调用，这也意味着这个时候任何诸如AOP拦截器之类的将不能被应用
 */
@Component
public class MyIOCBean implements InitializingBean, DisposableBean, BeanFactoryAware, BeanNameAware, SmartLifecycle {
    @Value("ok")
    private String name;
    @Resource
    private MyBean myBean;

    public MyIOCBean() {
        System.out.println("构造函数 初始化...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 代替 init-method
     */
    @PostConstruct
    public void testPostConstruct() {
        System.out.println("PostConstruct...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy...");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean...");
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware..." + beanFactory.getBean("myIOCBean"));
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("BeanNameAware..." + s);
    }

    private volatile boolean running = false;

    /**
     * 如果该`Lifecycle`类所在的上下文在调用`refresh`时,希望能够自己自动进行回调，则返回`true`* ,
     * false的值表明组件打算通过显式的start()调用来启动，类似于普通的Lifecycle实现。
     */
    @Override
    public boolean isAutoStartup() {
        return true;
    }

    /**
     * 很多框架中的源码中，都会把真正逻辑写在stop()方法内。
     * 比如quartz和Redis的spring支持包
     *
     * @param callback
     */
    @Override
    public void stop(Runnable callback) {
        System.out.println("smartLifecycle stop runnable 容器停止...");
        stop();
        callback.run();
    }

    @Override
    public void start() {
        System.out.println("smartLifecycle 容器启动完成 ...");
        running = true;
    }

    @Override
    public void stop() {
        System.out.println("smartLifecycle stop 容器停止 ...");
        running = false;
    }

    @Override
    public boolean isRunning() {
        System.out.println("smartLifecycle 检查运行状态 ...");
        return running;
    }

    /**
     * 阶段值   越小越靠前执行start()方法，越靠后执行stop()方法
     *
     * @return
     */
    @Override
    public int getPhase() {
        return 0;
    }
}
