package com.marshio.aop.dao.impl;

import com.marshio.aop.dao.StaticDao;

/**
 * @author masuo
 * @data 24/1/2022 下午2:34
 * @Description 被代理对象
 */

public class UserDaoImpl implements StaticDao {
    @Override
    public int insert() {
        System.out.println("focus on insert into user ...");
        return 0;
    }

    @Override
    public int delete() {
        System.out.println("focus on insert into user ...");
        return 0;
    }
}
