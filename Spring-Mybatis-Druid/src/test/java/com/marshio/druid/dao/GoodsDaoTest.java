package com.marshio.druid.dao;

import com.marshio.druid.pojo.Goods;
import com.marshio.druid.utils.MybatisUtil;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

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