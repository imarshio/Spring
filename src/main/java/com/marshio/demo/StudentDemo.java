package com.marshio.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author masuo
 * @data 12/1/2022 下午12:37
 * @Description 注解测试
 */

public class StudentDemo {

    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object stu = app.getBean("stu");
        System.out.println(stu);

        //这一步是为了销毁对象，触发销毁函数
        stu = null;
    }
}
