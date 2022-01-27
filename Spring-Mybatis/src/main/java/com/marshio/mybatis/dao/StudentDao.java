package com.marshio.mybatis.dao;

import com.marshio.mybatis.pojo.Student;

import java.util.List;

/**
 * @author masuo
 * @data 25/1/2022 下午2:56
 * @Description 学生 接口
 */
public interface StudentDao {
    int addStudent(Student student);
    int addStudentBacKey(Student student);
    int delStudent(String stuNum);
    List<Student> queryStudents();
    Student queryStudentByNum(String stuNum);
    Student queryStudentByNumWithAlias(String stuNum);
    int updateStudentNoResult(Student student);
    Student updateStudentBack(Student student);
}
