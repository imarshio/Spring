package com.marshio.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author masuo
 * @data 24/1/2022 下午4:14
 * @Description 获取代理类
 */

public class JDKDynamicProxy implements InvocationHandler {
    private final Object real;
    public JDKDynamicProxy(Object obj){
        this.real = obj;
    }

    public Object getProxy(){
        //想要生成代理对象，就需要知道被代理对象的类加载器和接口类
        ClassLoader classLoader= real.getClass().getClassLoader();
        Class<?>[] interfaces = real.getClass().getInterfaces();
        /*
        * 我们需要使用Proxy的newProxyInstance方法，一共需要三个参数
        * 1、ClassLoader，类加载器
        * 2、Class<?>[] interfaces，接口类集合
        * 3、InvocationHandler ，调用处理程序，即使用产生的代理对象调用方法时，用于拦截方法执行的处理器
        * */
        return Proxy.newProxyInstance(classLoader, interfaces, this);
    }

    /**
     * 调用方法，并做相应的处理
     * @param proxy 代理类
     * @param method 调用的方法（代理类）
     * @param args 方法参数
     * @return Object
     * @throws Throwable ex
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        begin();
        // 第一个参数：那个对象调用这个函数
        // 第二个参数，被调用的函数的参数
        Object invoke = method.invoke(real, args);
        commit();
        return invoke;
    }

    public void begin(){
        System.out.println("开启事务...");
    }

    public void commit(){
        System.out.println("提交事务...");
    }
}
