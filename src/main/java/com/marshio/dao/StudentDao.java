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
     * 增
     */
    public int insertStudent(Student student);

    /**
     * 删
     * @param stuNum 学生学号
     */
    public int deleteStudent(String stuNum);

    /**
     *
     * @param student 学生信息
     * @return 成功是否
     */
    public int updateStudent(Student student);
}
