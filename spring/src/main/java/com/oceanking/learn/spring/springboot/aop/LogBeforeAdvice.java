package com.oceanking.learn.spring.springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogBeforeAdvice {

    @Pointcut("execution(* com.oceanking.learn..*.*(..))")
    public void testPointcut() {
        System.out.println("-----------");
    }

    @Before("testPointcut()")
    public void before(JoinPoint joinPoint) {
        //System.out.println("Logging before " + joinPoint.getSignature().getName());
    }

}