package com.marshio.factory;

import com.marshio.dao.UserDao;
import com.marshio.dao.impl.UserDaoImpl;

/**
 * @author masuo
 * @date 2022/01/10/ 下午9:39
 * @description
 */
public class DynamicFactory {

    /**
     * 建立工厂方法，然后在配置文件中调用此类,然后再用此类调用此方法
     */
    public UserDao getUserDao(){
        System.out.println("工厂方法调用...");
        return new UserDaoImpl();
    }
}
