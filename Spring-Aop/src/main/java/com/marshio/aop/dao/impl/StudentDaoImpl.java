package com.marshio.aop.dao.impl;

import com.marshio.aop.dao.StaticDao;

/**
 * @author masuo
 * @data 24/1/2022 下午2:21
 * @Description 被代理对象,
 */

public class StudentDaoImpl implements StaticDao {

    @Override
    public int insert() {
        System.out.println("focus on insert into student ...");
        return 1;
    }

    @Override
    public int delete() {
        System.out.println("focus on delete from student ...");
        return 1;
    }
}
