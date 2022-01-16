package com.marshio.dao;

import com.marshio.pojo.Student;

/**
 * @author masuo
 * @date 2022/01/15/ 下午3:28
 * @description
 */
public interface StudentDao {

    //数据库操作接口

    /**
     *
     */
    public int insertStudent(Student student);

    /**
     *
     * @param student 学生
     */
    public int deleteStudent(String stuNum);
}
