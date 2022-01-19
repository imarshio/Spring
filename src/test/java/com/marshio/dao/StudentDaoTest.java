package com.marshio.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marshio.pojo.Student;
import com.marshio.utils.MybatisUtils;
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
 * SqlSession:
 *      1、可以获取Mapper，即Dao层接口实例
 *      2、可以进行事务管理
 *          2.1、当我们获取一个session时，就相当于开启一个事务
 *          2.2、当我们调用sql失败或出现异常时，可以在catch里进行回滚
 */
public class StudentDaoTest {

    /**
     * 新增学生，不返回主键
     */
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

    /**
     * 新增学生，带主键，
     */
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

    /**
     * 删除学生
     */
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
            int i = sd.deleteStudent("20213608");
            //手动提交事务，不提交事务不会被完成
            sqlSession.commit();
            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新信息
     */
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

    /**
     * 返回所有的学生
     * 使用 resultType="com.marshio.pojo.Student"
     */
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

    /**
     * 返回所有的学生
     * 使用resultMap="studentMap"，需要定义Map属性
     */
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

    /**
     * 分页查询
     * 参数设置1
     */
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

    /**
     * 分页查询
     * 参数设置2
     */
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

    /**
     * 分页查询
     * 分页插件
     */
    @Test
    public void testListStudentsByPageInterceptor() {
        try {
            
            StudentDao studentDao = MybatisUtils.getMapper(StudentDao.class);
            PageHelper.startPage(1, 4);
            //查询操作不需要提交事务
            List<Student> students = studentDao.listStudents();
            PageInfo<Student> studentPageInfo = new PageInfo<Student>(students);
            List<Student> list = studentPageInfo.getList();
            for (Student student : list) {
                System.out.println(student.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 查询一个学生
     * 需要指定返回类型
     */
    @Test
    public void testQueryStudent() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(is);
            SqlSession sqlSession = factory.openSession();

            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            //查询操作不需要提交事务
            Student student = studentDao.queryStudent("20223601");
            System.out.println(student);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取总数
     * 需要指定返回类型
     */
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

    /**
     * 测试封装工具栏
     * 1.看sqlSessionFactory会被创建几次，即看静态代码块会被执行几次，1次，只有在类被加载的时候才会被执行，之后不再执行
     */
    @Test
    public void testMybatisUtils() {
        try {
            //获取SqlSession
            testMybatisUtilsSon1();
            testMybatisUtilsSon2();
            testMybatisUtilsSon3();
            //获取Mapper
            testMybatisUtilsSon4();
            testMybatisUtilsSon5();
            testMybatisUtilsSon6();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 等价于testGetCount
     */
    public void testMybatisUtilsSon1() {
        try {
            SqlSession sqlSession = MybatisUtils.getSqlSession();
            //根据类获取数据接口
            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            //查询操作不需要提交事务
            int i = studentDao.getCount();
            System.out.println(i);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void testMybatisUtilsSon2() {
        try {
            SqlSession sqlSession = MybatisUtils.getSqlSession();
            //根据类获取数据接口
            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            //查询操作不需要提交事务
            int i = studentDao.getCount();
            System.out.println(i);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void testMybatisUtilsSon3() {
        try {
            SqlSession sqlSession = MybatisUtils.getSqlSession();
            //根据类获取数据接口
            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

            //查询操作不需要提交事务
            int i = studentDao.getCount();
            System.out.println(i);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void testMybatisUtilsSon4() {
        try {
            //根据类获取数据接口
            StudentDao studentDao = MybatisUtils.getMapper(StudentDao.class);

            //查询操作不需要提交事务
            int i = studentDao.getCount();
            System.out.println(i);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void testMybatisUtilsSon5() {
        try {
            //根据类获取数据接口
            StudentDao studentDao = MybatisUtils.getMapper(StudentDao.class);

            //查询操作不需要提交事务
            int i = studentDao.getCount();
            System.out.println(i);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void testMybatisUtilsSon6() {
        try {
            //根据类获取数据接口
            StudentDao studentDao = MybatisUtils.getMapper(StudentDao.class);

            //查询操作不需要提交事务
            int i = studentDao.getCount();
            System.out.println(i);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testSqlSession() {

        //获取SqlSession，默认不自动提交事务
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try {
            StudentDao sd = sqlSession.getMapper(StudentDao.class);

            Student student = new Student("20173605", "mas", "1", 20);
            //测试方法
            int i = sd.insertStudent(student);
            //此时获取到的主键值为 0
            System.out.println(student);
            //手动提交事务，不提交事务不会被完成
            sqlSession.commit();
            System.out.println(i);

        }catch (Exception e){
            //插入异常，则进行回滚，多用于多条语句执行时，前面语句成功，后面的语句没有成功
            // 如，事务场景为，插入一条数据，然后修改其中内容，插入成功，但是修改失败，这时就会需要回滚
            sqlSession.rollback();
        }
    }

}