package com.marshio.mybatis.demo;

import com.marshio.mybatis.dao.GoodsDao;
import com.marshio.mybatis.dao.StudentDao;
import com.marshio.mybatis.pojo.Goods;
import com.marshio.mybatis.pojo.Student;
import com.marshio.mybatis.utils.MybatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author masuo
 * @data 25/1/2022 下午3:19
 * @Description mybatis
 */

public class MybatisDemo {

    private static final Logger logger = Logger.getLogger(MybatisDemo.class);
    @Test
    public void testMybatis() {
        try {
            //6、所以我们需要想办法构造InputStream
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            //4、SqlSessionFactoryBuilder可以被new
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //3、SqlSessionFactory也不能被new，只能被SqlSessionFactoryBuilder生成,
            // 5、但是builder需要传入一个参数
            SqlSessionFactory factory = builder.build(is);
            //1、在使用mybatis时，我们需要使用sqlSession来管理与数据库的连接
            //2、但是sqlSession不能被new，只能被sqlSessionFactory.open()生成
            SqlSession sqlSession = factory.openSession();

            //7、下面，我们来看看sqlSession能做些什么吧,可以看到他能提交事务，回滚事务等各种操作
            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
            int i = studentDao.addStudent(new Student("1000023", "mass", "0", 100));
            System.out.println(i);

            //注意，如果不手动提交事务，数据库是不会被更改的
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMybatisUtils1() {
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession();
            //7、下面，我们来看看sqlSession能做些什么吧,可以看到他能提交事务，回滚事务等各种操作
            StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
            int i = studentDao.addStudent(new Student("1000024", "mass", "0", 100));
            System.out.println(i);
            //无需手动提交
            //sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMybatisUtils2() {
        try {
            StudentDao studentDao = MybatisUtil.getMapper(StudentDao.class);
            int i = studentDao.delStudent("1000023");
            System.out.println(i);
            //无需手动提交
            //sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试并发下封装类是否会发生事务冲突
     */
    @Test
    public void testMybatisUtils3() {

        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            service.execute(() -> {
                SqlSession sqlSession = MybatisUtil.getSqlSession();
                //System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                SqlSession sqlSession1 = MybatisUtil.getSqlSession();
                if (sqlSession != sqlSession1) {

                    System.out.println(sqlSession + " " + sqlSession1);
                }
            });
        }
        service.shutdown();
        try {
            service.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试日志的功能
     * @param args 参数
     */
    public static void main(String[] args) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        GoodsDao mapper = sqlSession.getMapper(GoodsDao.class);
        List<Goods> goods = mapper.queryGoods();
        logger.debug("first");
        logger.info("search all");
        Stream<Goods> stream = goods.stream();
        System.out.println(stream.count());
    }

}
