package com.marshio;

import com.marshio.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author masuo
 * @date 2022/01/10/ 下午9:36
 * @description
 */
public class UserDaoTest {

    @Test
    public void getUserDaoTest(){

        //找到配置文件，并加载配置文件
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

        //利用Spring容器获取Bean对象的实例
        UserDao userDao = (UserDao) app.getBean("userDao");

        //调用对象的方法
        userDao.save();
    }
}
