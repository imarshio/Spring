package com.marshio.aop.demo;

import com.marshio.aop.pojo.Student;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author masuo
 * @data 24/1/2022 上午9:01
 * @Description 反射，实验 AOP 是如何利用反射获取对象的
 */

public class ReflectDemo {

    /**
     * 利用反射获取类
     */
    @Test
    public void reflectMethod() {
        //1、ClassName.class
        //2、classInstance.getClass()
        //3、Class.forName("类的全限定名");为避免类的全限定名输入错误，需要捕获异常

        Class<Student> studentClass = Student.class;
        System.out.println(studentClass.getName());

        Student student = new Student();
        System.out.println(student.getClass().getName());

        try {
            //需要使用泛型来接收
            Class<?> aClass = Class.forName("com.marshio.aop.pojo.Student");
            System.out.println(aClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 利用反射获取调用类的属性方法
     */
    @Test
    public void reflectTest() {
        Class<Student> studentClass = Student.class;
        try {
            //先获取类构造器
            Constructor<Student> studentConstructor = studentClass.getDeclaredConstructor();
            System.out.println(studentConstructor);
            //设置类的构造器可达，避免私有类报错
            studentConstructor.setAccessible(true);
            //获取对象的参数类型以及name
            Field[] fields = studentClass.getDeclaredFields();
            List<String> methods = new ArrayList<>();
            for (Field field : fields) {
                System.out.println(field + " " + field.getName());
                //set + 属性名称第一个字母大写 + 属性名出第一个字母之后的
                methods.add("set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
            }

            for (String s : methods) {
                System.out.println(s);
            }
            //声明对象
            Student student = studentConstructor.newInstance();
            System.out.println(student.toString());

            Method[] declaredMethods = studentClass.getDeclaredMethods();
            for (Method method : declaredMethods) {
                System.out.println(method + " " + method.getName());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
