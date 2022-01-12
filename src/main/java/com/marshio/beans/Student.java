package com.marshio.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author masuo
 * @data 12/1/2022 下午12:33
 * @Description 注解的使用
 *
 * Component注解相当于在配置文件中写了一条bean语句，将该对象交由Spring容器管理,且对象id就是注解参数
 * Component("stu") = Service("stu") = Controller("stu") = Repository("stu)
 * 但它们存在语义上的不同
 * Component  ： 主要声明除以下类之外的所有类的处理
 * Service    ： 主要声明业务处理类，Service接口的实现类
 * Controller ： 主要将控制器类交由Spring管理，即Controller层，Servlet层
 * Repository ： 主要声明持久化类，Dao接口层
 *
 * Scope注解用于声明该对象是单例模式还是原型模式，默认为单例模式
 * Lazy注解用于声明该对象在单例模式下是懒汉还是饿汉
 */

@Component("stu")
@Scope("prototype")
@Lazy(false)
public class Student {

    private String uNum;
    private String uName;
    private int uAge;

    /**
     * Autowired：自动装配类型，默认按照类型装配 byType，找不到类型匹配的则抛出异常
     * required = false,设置自动装配是否必须，为false时找不到类型匹配的则赋空值，不抛出异常
     */
    @Autowired(required = false)
    public Clazz clazz;

    /**
     * 默认是byName，找不到name匹配的就按照byType，byType没找到或找到多个就会抛出异常
     */
    @Resource
    public Clazz clazz2;
    /**
     * 构造函数
     */
    public Student(){
        System.out.println("construct。。");
    }
    /**
     * PostConstruct：在构造函数执行完之后执行
     * 初始化函数
     */
    @PostConstruct
    public void init(){
        System.out.println("init。。");
    }

    /**
     * PreDestroy：在回收对象之前执行
     * 销毁函数
     */
    @PreDestroy
    public void free(){
        System.out.println("free。。");
    }

    @Override
    public String toString() {
        return "Student{" +
                "uNum='" + uNum + '\'' +
                ", uName='" + uName + '\'' +
                ", uAge=" + uAge +
                ", clazz=" + clazz +
                '}';
    }

    public String getuNum() {
        return uNum;
    }

    public void setuNum(String uNum) {
        this.uNum = uNum;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public int getuAge() {
        return uAge;
    }

    public void setuAge(int uAge) {
        this.uAge = uAge;
    }

    public Clazz getClazz() {
        return clazz;
    }

    /**
     * Qualifier:等于xml中的byName
     */
    public void setClazz(@Qualifier("clazz") Clazz clazz) {
        this.clazz = clazz;
    }
}
