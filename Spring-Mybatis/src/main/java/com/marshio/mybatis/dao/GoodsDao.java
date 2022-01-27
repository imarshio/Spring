package com.marshio.mybatis.dao;

import com.marshio.mybatis.dao.conditions.MemberSearchCondition;
import com.marshio.mybatis.pojo.Goods;

import java.util.HashMap;
import java.util.List;

/**
 * @author masuo
 * @data 27/1/2022 上午11:41
 * @Description 商品 持久层
 */

public interface GoodsDao {

    public int addGoods(Goods goods);
    public int delGoodsById(int gid);
    public List<Goods> queryGoods();

    public List<Goods> queryGoodsWithMap(HashMap<String, Object> params);
    public List<Goods> queryGoodsWithParams(MemberSearchCondition params);
    public List<Goods> queryGoodsWithTrim(MemberSearchCondition params);
    public List<Goods> queryGoodsWithForEach(MemberSearchCondition params);
    public int updateGoods(Goods goods);
}
