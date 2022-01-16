package com.marshio.dao;

import com.marshio.pojo.Student;
import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author masuo
 * @date 2022/01/15/ 下午3:58
 * @description 单元测试
 */
public class StudentDaoTest {

    @Test
    public void testInsertStudent() {

        try {
            //加载配置文件
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            //构造器
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //会话工厂
            SqlSessionFactory factory = builder.build(is);
            //会话-连接
            SqlSession sqlSession = factory.openSession();

            StudentDao sd = sqlSession.getMapper(StudentDao.class);

            System.out.println(sd);

            //测试方法
            int i = sd.insertStudent(new Student("20173602", "mas", "1", 20));
            //手动提交事务，不提交事务不会被完成
            sqlSession.commit();
            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testDeleteStudent() {
        //加载配置文件
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
            //构造器
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //会话工厂
            SqlSessionFactory factory = builder.build(is);
            //会话-连接
            SqlSession sqlSession = factory.openSession();

            StudentDao sd = sqlSession.getMapper(StudentDao.class);

            System.out.println(sd);

            //测试方法
            int i = sd.deleteStudent("20173602");
            //手动提交事务，不提交事务不会被完成
            sqlSession.commit();
            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}