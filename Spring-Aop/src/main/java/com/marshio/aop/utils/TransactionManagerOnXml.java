package com.marshio.aop.utils;

/**
 * @author masuo
 * @data 25/1/2022 上午8:21
 * @Description XML配置式事务管理
 */

public class TransactionManagerOnXml {

    public void begin(){
        System.out.println("开启事务...");
    }

    public void commit(){
        System.out.println("提交事务...");
    }


}
