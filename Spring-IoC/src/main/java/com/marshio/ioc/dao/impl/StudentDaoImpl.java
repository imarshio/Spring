package com.marshio.ioc.dao.impl;

import com.marshio.ioc.dao.StudentIocDao;
import com.marshio.ioc.pojo.Student;

/**
 * @author masuo
 * @data 21/1/2022 下午4:18
 * @Description 接口实现
 */

public class StudentDaoImpl implements StudentIocDao {

    public void queryStudentOnIoc() {
        Student student = new Student("2017","mas","1",20);
        System.out.println(student.toString());
    }
}
