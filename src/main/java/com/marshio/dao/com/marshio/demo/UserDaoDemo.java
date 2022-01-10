package com.marshio.dao.com.marshio.demo;

import com.marshio.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author masuo
 * @date: 2022/01/09/ 下午8:17
 * @description
 */
public class UserDaoDemo {
    public static void main(String[] args) {

        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) app.getBean("userDao");
        userDao.save();
    }
}
