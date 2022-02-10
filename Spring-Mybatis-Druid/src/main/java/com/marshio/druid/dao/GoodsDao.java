package com.marshio.druid.dao;

import com.marshio.druid.dao.conditions.MemberSearchCondition;
import com.marshio.druid.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author masuo
 * @data 27/1/2022 上午11:41
 * @Description 商品 持久层
 */


public interface GoodsDao {

    int addGoods(Goods goods);

    int delGoodsById(int gid);

    Goods queryGoodsById(int gid);

    List<Goods> queryGoods();

    List<Goods> queryGoodsWithMap(HashMap<String, Object> params);

    List<Goods> queryGoodsWithParams(MemberSearchCondition params);

    List<Goods> queryGoodsWithTrim(MemberSearchCondition params);

    List<Goods> queryGoodsWithForEach(List<?> params);

    /**
     * 模糊查询
     * 需要注意，在使用模糊查询的时候，我们需要使用${}注入参数，而不是#{}
     * 与此同时，在使用${}的时候，即使只有一个参数也需要使用@Param注解声明参数的key（非String不需要）
     * @param key 关键词
     * @return list
     */
    List<Goods> queryGoodsByKeys(@Param("key") String key);

    int updateGoods(Goods goods);
}
