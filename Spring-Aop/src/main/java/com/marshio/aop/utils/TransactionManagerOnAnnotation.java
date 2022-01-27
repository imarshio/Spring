package com.marshio.aop.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author masuo
 * @data 25/1/2022 上午8:21
 * @Description 注解式事务管理 --- 虽然是注解类，但是步骤都是一样的
 * Component：表示将此类交给Spring容器管理，是一个对象的意思
 * Aspect：表示这个类是一个切面类
 */
@Component
@Aspect
public class TransactionManagerOnAnnotation {

    //声明切入点
    @Pointcut("execution(* com.marshio.aop.dao.*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void begin() {
        System.out.println("开启事务...");
    }

    @After("pointCut()")
    public void commit() {
        System.out.println("提交事务...");
    }

    @AfterThrowing("pointCut()")
    public void afterThrow() {
        System.out.println("事务回滚。。。");
    }

    @AfterReturning("pointCut()")
    public void afterReturn() {
        //AfterReturning == After , 因为方法返回值返回之后，对于一个Java方法而言return返回值也是方法的一部分
        System.out.println("提交事务。。。");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) {
        System.out.println("");
        long begin = System.currentTimeMillis();
        Object o = null;
        try {
            o = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long commit = System.currentTimeMillis();
        System.out.println("time:"+(commit-begin));
        return o;
    }
}
