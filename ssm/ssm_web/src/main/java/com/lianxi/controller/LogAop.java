package com.lianxi.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAop {
   //前置通知  获取执行开始的时间，方法，类
    @Before("execution(* com.lianxi.controller.*.*(..))")
    public void before(JoinPoint jp){

  }

    //后置通知
    @Before("execution(* com.lianxi.controller.*.*(..))")
    public void doAfter(JoinPoint jp){

    }
}
