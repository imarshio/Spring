package com.marshio.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author masuo
 * @data 18/1/2022 下午12:50
 * @Description 封装 Mybatis 工具类
 */

public class MybatisUtils {

    //声明工厂类
    private static SqlSessionFactory sqlSessionFactory;
    private static final ThreadLocal<SqlSession> local = new ThreadLocal<SqlSession>();

    //静态代码块只会执行一次
    static {
        try {
            System.out.println("生成SqlSessionFactory。。。");
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 手动管理事务
     *
     * @return SqlSession
     */
    public static SqlSession getSqlSession() {
        //在线程获取SQL Session
        return getSqlSession(false);
    }

    /**
     * 生成SqlSession 的　API接口
     *
     * @return SqlSession实例
     */
    public static SqlSession getSqlSession(boolean autoCommit) {
        //在线程获取SQL Session
        SqlSession sqlSession = local.get();
        if (sqlSession == null) {
            // 如果为空，就打开一个会话，并放到ThreadLocal
            //这样避免了每次都会打开一个会话，造成资源的浪费。
            sqlSession = sqlSessionFactory.openSession(autoCommit);
            local.set(sqlSession);
        }
        return sqlSession;
    }

    public static <T> T getMapper(Class<T> clazz) {
        // 直接调用该方法，表明不管理事务，即自动提交
        SqlSession sqlSession = getSqlSession(true);
        //然后根据SqlSession获取Mapper对象
        return sqlSession.getMapper(clazz);
    }
}
