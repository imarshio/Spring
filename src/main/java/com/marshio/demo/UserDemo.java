package com.marshio.demo;

import com.marshio.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author masuo
 * @date 2022/01/09/ 下午8:17
 * @description
 */
public class UserDemo {
    public static void main(String[] args) {

        //初始化Spring容器，即找到配置文件，并加载配置文件
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");

        //利用Spring容器获取Bean对象的实例
        User user = (User) app.getBean("user");

        //调用对象的方法
        System.out.println(user.toString());
    }
}
