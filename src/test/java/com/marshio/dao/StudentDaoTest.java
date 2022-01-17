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
        InputStream is ;
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
            int i = sd.deleteStudent("1151511");
            //手动提交事务，不提交事务不会被完成
            sqlSession.commit();
            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateStudent() {
        try {
            // 获取mybatis连接属性
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            //创建构造器
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            //通过构造器创建工厂
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
            //获取sqlSession，即开启一个与数据库交互的会话事务
            SqlSession sqlSession = sqlSessionFactory.openSession();
            //通过会话获取数据接口层的
            // <T> T getMapper(Class<T> var1);
            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
            //获取到数据接口类，调用具体方法，
            int i = studentDao.updateStudent(new Student(5, "20223605", "22222", "2", 20));
            //看执行成功与否
            sqlSession.commit();
            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}