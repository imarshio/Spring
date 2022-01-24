package com.marshio.aop.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author masuo
 * @data 24/1/2022 下午4:59
 * @Description 获取动态代理
 */

public class CGLibDynamicProxy implements MethodInterceptor {

    private final Object real;
    public CGLibDynamicProxy(Object obj){
        this.real = obj;
    }

    public Object getProxy(){
        //与JDK类似,
        Enhancer enhancer = new Enhancer();
        //设置该类为其子类，被代理类为其父类
        enhancer.setSuperclass(real.getClass());
        //回调函数
        enhancer.setCallback(this);
        //创建代理对象
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        begin();
        //通过反射调用代理类
        // 第一个参数：那个对象调用这个函数
        // 第二个参数，被调用的函数的参数
        Object returnV = method.invoke(real, objects);
        commit();
        return returnV;
    }

    public void begin(){
        System.out.println("开启事务...");
    }

    public void commit(){
        System.out.println("提交事务...");
    }
}
