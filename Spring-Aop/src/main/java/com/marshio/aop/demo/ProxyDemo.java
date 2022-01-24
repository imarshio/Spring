package com.marshio.aop.demo;

import com.marshio.aop.dao.StaticDao;
import com.marshio.aop.dao.impl.StudentDaoImpl;
import com.marshio.aop.dao.impl.UserDaoImpl;
import com.marshio.aop.proxy.CGLibDynamicProxy;
import com.marshio.aop.proxy.JDKDynamicProxy;
import com.marshio.aop.proxy.StaticProxy;
import org.junit.Test;

/**
 * @author masuo
 * @data 24/1/2022 下午2:16
 * @Description 代理
 */

public class ProxyDemo {
    /**
     * 静态代理
     */
    @Test
    public void staticProxyTest(){

        StudentDaoImpl studentDao = new StudentDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();
        //这里我们只需要调用代理类即可
        StaticProxy proxy1 = new StaticProxy(studentDao);
        System.out.println(proxy1.insert());
        System.out.println(proxy1.delete());
        StaticProxy proxy2 = new StaticProxy(userDao);
        System.out.println(proxy2.insert());
        System.out.println(proxy2.delete());
    }

    /**
     * 动态代理
     */
    @Test
    public void dynamicProxyTest(){
        JDKDynamicProxy();
        CGLibDynamicProxy();
    }

    /**
     * JDK动态代理通过 获取被代理对象实现的接口 产生 代理对象
     * 即我们此时只有 被代理对象 以及其实现的接口，然后
     */
    public void JDKDynamicProxy() {
        UserDaoImpl userDao = new UserDaoImpl();
        JDKDynamicProxy jdkDynamicProxy = new JDKDynamicProxy(userDao);
        StaticDao dao = (StaticDao) jdkDynamicProxy.getProxy();
        dao.insert();
        dao.delete();
    }

    public void CGLibDynamicProxy() {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        CGLibDynamicProxy cgLibDynamicProxy = new CGLibDynamicProxy(studentDao);
        StaticDao dao = (StaticDao) cgLibDynamicProxy.getProxy();
        dao.insert();
        dao.delete();
    }
}
