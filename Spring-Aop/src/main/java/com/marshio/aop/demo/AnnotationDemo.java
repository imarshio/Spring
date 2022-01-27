package com.marshio.aop.demo;

import com.marshio.aop.dao.StaticDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author masuo
 * @data 25/1/2022 上午10:02
 * @Description 注解案例
 */

public class AnnotationDemo {

    @Test
    public void testAnnotation() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        StaticDao userDao = (StaticDao) app.getBean("userDao");
        userDao.insert();
    }
}
