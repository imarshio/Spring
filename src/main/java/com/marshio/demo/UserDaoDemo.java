package com.marshio.demo;

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

        //找到配置文件，并加载配置文件
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

        //利用Spring容器获取Bean对象的实例
        UserDao userDao = (UserDao) app.getBean("userDao");

        //调用对象的方法
        userDao.save();
    }
}
