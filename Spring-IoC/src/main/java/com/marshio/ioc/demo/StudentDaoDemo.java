package com.marshio.ioc.demo;

import com.marshio.ioc.dao.StudentIocDao;
import com.marshio.ioc.pojo.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author masuo
 * @data 21/1/2022 下午3:47
 * @Description 测试Spring的容器管理
 */

public class StudentDaoDemo {

    @Test
    public void springIoCTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取无参对象
        StudentIocDao studentDao = (StudentIocDao) applicationContext.getBean("studentDao1");
        studentDao.queryStudentOnIoc();

        //获取依赖注入含参数对象
        Student student = (Student) applicationContext.getBean("student");
        System.out.println(student.toString());
    }
}
