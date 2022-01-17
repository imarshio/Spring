package com.marshio.dao;

import com.marshio.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    int insertStudent(Student student);

    int insertStudent2(Student student);

    /**
     * 删
     * @param stuNum 学生学号
     */
    int deleteStudent(String stuNum);

    /**
     *
     * @param student 学生信息
     * @return 成功是否
     */
    int updateStudent(Student student);

    List<Student> listStudents();

    List<Student> listStudents2();

    /**
     * 在mybatis中进行条件查询，
     * 如果条件查询方法只有一个简单类型或字符串参数，在mapper中可以直接#{除数字外的任意字符串，建议参数名称}获取
     * 如果操作方法有一个对象类型参数，在mapper中可以通过#{proName}获取对象指定属性值，proName必须是对象属性名称，如Studeng的stuNum
     * 如果给两个参数，那么就需要通过如下方法
     *      1.map参数，在mapper中可以直接通过key获取对应的值
     *      2.可以通过#{arg0}、#{arg1}、#{param0}、#{param1}取值
     *      或者通过 @param
     * @param start 分页开始
     * @param pageSize 分页大小
     * @return List<Student>
     */
    List<Student> listStudentsByPage(int start, int pageSize);

    List<Student> listStudentsByPage2(@Param("start") int start,
                                     @Param("pageSize") int pageSize);

    Student queryStudent(String stuNum);

    int getCount();

}
