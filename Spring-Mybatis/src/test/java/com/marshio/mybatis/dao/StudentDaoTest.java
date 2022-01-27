package com.marshio.mybatis.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marshio.mybatis.pojo.Student;
import com.marshio.mybatis.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;


/**
 * @author masuo
 * @data 26/1/2022 下午1:36
 * @Description 测试
 */
public class StudentDaoTest {

    @Test
    public void addStudent() {
        StudentDao studentDao = MybatisUtil.getMapper(StudentDao.class);
        int i = studentDao.addStudent(new Student("1000023", "mass", "0", 100));
        System.out.println(i);
    }

    @Test
    public void delStudent() {
        StudentDao studentDao = MybatisUtil.getMapper(StudentDao.class);
        int i = studentDao.delStudent("1000023");
        System.out.println(i);
    }

    @Test
    public void queryStudents() {
        StudentDao mapper = MybatisUtil.getMapper(StudentDao.class);
        //设置页偏移量和页容量
        PageHelper.startPage(2,4);
        //查询到所需学生
        List<Student> students = mapper.queryStudents();
        //放入到pageInfo中,pageInfo中就包含数据及分页信息
        PageInfo<Student> pageInfo = new PageInfo<>(students);
        //获取list
        List<Student> list = pageInfo.getList();

        for (Student student : list) {
            System.out.println(student);
        }
    }

    @Test
    public void queryStudentByNum() {
        StudentDao studentDao = MybatisUtil.getMapper(StudentDao.class);
        Student student = studentDao.queryStudentByNum("20223603");
        System.out.println(student.toString());
    }

    @Test
    public void queryStudentByNumWithAlias() {
        StudentDao studentDao = MybatisUtil.getMapper(StudentDao.class);
        Student student = studentDao.queryStudentByNumWithAlias("1000023");
        System.out.println(student.toString());
    }

    @Test
    public void updateStudentNoResult() {
        StudentDao studentDao = MybatisUtil.getMapper(StudentDao.class);
        int student = studentDao.updateStudentNoResult(new Student(16,"20173605", "mass", "0", 100));
        System.out.println(student);
    }

    @Test
    public void updateStudentBack() {
        StudentDao studentDao = MybatisUtil.getMapper(StudentDao.class);
        Student student = studentDao.queryStudentByNumWithAlias("1000023");
        System.out.println(student.toString());
    }
}