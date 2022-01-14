package com.marshio.utils;

/**
 * @author masuo
 * @date 2022/01/13/ 下午7:20
 * @description 事务管理
 * 需要配置启用此类
 */
public class TxManager {

    public void begin(){

        System.out.println("开启事务...");
    }

    public void commit(){

        System.out.println("结束事务...");
    }
}
