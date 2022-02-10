package com.marshio.druid.dao;

import com.marshio.druid.pojo.Goods;
import com.marshio.druid.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;


/**
 * @author masuo
 * @data 7/2/2022 下午2:40
 * @Description 测试
 */
public class GoodsDaoTest {
    private static final Logger logger = Logger.getLogger(GoodsDaoTest.class);

    @Test
    public void addGoods() {
    }

    @Test
    public void delGoodsById() {
    }

    @Test
    public void queryGoods() {
        GoodsDao mapper = MybatisUtil.getMapper(GoodsDao.class);
        List<Goods> goods = mapper.queryGoods();
        logger.info("search all.");
        for (Goods good : goods) {
            System.out.println(good.toString());
        }
    }

    @Test
    public void cacheTest() {
        SqlSessionFactory factory = MybatisUtil.getFactory();
        //多个SQL Session对象必须来自同一个SqlSessionFactory，二级缓存即SqlSessionFactory级的缓存
        SqlSession sqlSession1 = factory.openSession();
        SqlSession sqlSession2 = factory.openSession();
        System.out.println(sqlSession1==sqlSession2);
        GoodsDao mapper = sqlSession1.getMapper(GoodsDao.class);
        Goods goods1 = mapper.queryGoodsById(1);
        System.out.println(goods1);
        //事务执行完毕会将数据放入缓存
        sqlSession1.commit();

        System.out.println("...........");
        //在这里手动修改数据库，在查询
        GoodsDao mapper1 = sqlSession2.getMapper(GoodsDao.class);
        Goods goods2 = mapper1.queryGoodsById(1);
        System.out.println(goods2);
    }

    @Test
    public void queryGoodsWithMap() {
    }

    @Test
    public void queryGoodsWithParams() {
    }

    @Test
    public void queryGoodsWithTrim() {
    }

    @Test
    public void queryGoodsWithForEach() {
    }

    @Test
    public void queryGoodsByKeys() {
    }

    @Test
    public void updateGoods() {
    }
}