package com.marshio.factory;

import com.marshio.dao.UserDao;
import com.marshio.dao.impl.UserDaoImpl;

/**
 * @author masuo
 * @date 2022/01/10/ 下午9:30
 * @description 利用工厂静态方法实例化对象
 */
public class StaticFactory {

    /**
     * 建立静态工厂方法，然后在配置文件中调用此类即可,因为静态方法会自动执行
     */
    public static UserDao getUserDao(){
        System.out.println("静态方法调用...");
        return new UserDaoImpl();
    }
}
