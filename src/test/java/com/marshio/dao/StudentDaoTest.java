package com.marshio.dao;

import com.marshio.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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

            Student student = new Student("20173605", "mas", "1", 20);
            //测试方法
            int i = sd.insertStudent(student);
            //此时获取到的主键值为 0
            System.out.println(student);
            //手动提交事务，不提交事务不会被完成
            sqlSession.commit();
            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testInsertStudent2() {

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
            Student student = new Student("20173605", "mas", "1", 20);
            //测试方法
            int i = sd.insertStudent2(student);
            //此时返回的学生是附带主键的
            System.out.println(student);
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
            int i = sd.deleteStudent("20173602");
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

    @Test
    public void testListStudent() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(is);
            SqlSession sqlSession = factory.openSession();

            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
            List<Student> students = studentDao.listStudents();
            for (Student student : students) {
                System.out.println(student.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testListStudent2() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(is);
            SqlSession sqlSession = factory.openSession();

            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            //查询操作不需要提交事务
            List<Student> students = studentDao.listStudents2();
            for (Student student : students) {
                System.out.println(student.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testListStudentsByPage() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(is);
            SqlSession sqlSession = factory.openSession();

            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            //查询操作不需要提交事务
            List<Student> students = studentDao.listStudentsByPage(2,5);
            for (Student student : students) {
                System.out.println(student.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testListStudentsByPage2() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(is);
            SqlSession sqlSession = factory.openSession();

            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            //查询操作不需要提交事务
            List<Student> students = studentDao.listStudentsByPage(2,5);
            for (Student student : students) {
                System.out.println(student.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryStudent() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(is);
            SqlSession sqlSession = factory.openSession();

            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            //查询操作不需要提交事务
            Student student = studentDao.queryStudent("20173602");
            System.out.println(student);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCount() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(is);
            SqlSession sqlSession = factory.openSession();

            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            //查询操作不需要提交事务
            int i = studentDao.getCount();
            System.out.println(i);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
}