package com.marshio.druid.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author masuo
 * @data 25/1/2022 下午5:25
 * @Description 工具类
 */

public class MybatisUtil {
    private static final ThreadLocal<SqlSession> local = new ThreadLocal<>();
    private static SqlSessionFactory factory;

    static {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getFactory() {
        return factory;
    }

    /**
     * @param autoCommit 是否自动提交
     * @return SqlSession
     */
    public static SqlSession getSqlSession(boolean autoCommit) {
        SqlSession sqlSession = local.get();
        if (sqlSession == null) {
            sqlSession = factory.openSession(autoCommit);
            local.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 无需手动提交的事务管理器
     *
     * @return SqlSession
     */
    public static SqlSession getSqlSession() {
        return getSqlSession(true);
    }

    /**
     * 获取接口类
     * @param clazz 接口类
     * @param <T> 类型
     * @return <T>
     */
    public static <T> T getMapper(Class<? extends T> clazz) {
        SqlSession sqlSession = getSqlSession(true);
        return sqlSession.getMapper(clazz);
    }
}
