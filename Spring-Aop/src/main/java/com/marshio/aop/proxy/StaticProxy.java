package com.marshio.aop.proxy;

import com.marshio.aop.dao.StaticDao;

/**
 * @author masuo
 * @data 24/1/2022 下午2:22
 * @Description 静态代理类
 */

public class StaticProxy implements StaticDao {

    private static StaticDao staticDao;

    public StaticProxy(StaticDao real){
        staticDao = real;
    }

    @Override
    public int insert() {
        System.out.println("proxy working...");
        begin();
        staticDao.insert();
        commit();
        return 0;
    }

    @Override
    public int delete() {
        System.out.println("proxy working...");
        begin();
        staticDao.delete();
        commit();
        return 0;
    }

    public void begin(){
        System.out.println("开启事务。。。");
    }

    public void commit(){
        System.out.println("结束事务。。。");
    }
}
