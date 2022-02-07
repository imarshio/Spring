package com.marshio.mybatis.dao;

import com.marshio.mybatis.dao.conditions.MemberSearchCondition;
import com.marshio.mybatis.pojo.Goods;
import com.marshio.mybatis.utils.MybatisUtil;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author masuo
 * @data 27/1/2022 下午2:22
 * @Description 测试
 */
public class GoodsDaoTest {

    @Test
    public void addGoods() {
        GoodsDao mapper = MybatisUtil.getMapper(GoodsDao.class);
        //mapper.addGoods(new Goods(0,"Lenovo 2020",10,8000,"Lenovo","台",new Timestamp(System.currentTimeMillis()),"这是联想的拯救者！很贵的。"));
        //mapper.addGoods(new Goods(0,"HP 2020",10,4500,"HP","台",new Timestamp(System.currentTimeMillis()),"这是惠普的畅游人！还好的。"));
        //mapper.addGoods(new Goods(0,"HP 2021",10,7000,"HP","台",new Timestamp(System.currentTimeMillis()),"这是惠普的光影精灵者！一般贵的。"));
        //mapper.addGoods(new Goods(0,"HP 2022",10,12000,"HP","台",new Timestamp(System.currentTimeMillis()),"这是惠普的暗影精灵！很贵的。"));
        //mapper.addGoods(new Goods(0,"DELL 2020",10,4499,"DELL","台",new Timestamp(System.currentTimeMillis()),"这是戴尔的灵越！很好看的，很轻。"));
        //mapper.addGoods(new Goods(0,"DELL 2021",10,30000,"DELL","台",new Timestamp(System.currentTimeMillis()),"这是戴尔的外星人！很贵的。"));
        mapper.addGoods(new Goods(0,"DELL 2022",10,7999,"DELL","台",new Timestamp(System.currentTimeMillis()),"这是戴尔的XPS！很贵的。"));
    }

    @Test
    public void delGoodsById() {
    }

    @Test
    public void queryGoods() {
        GoodsDao mapper = MybatisUtil.getMapper(GoodsDao.class);
        List<Goods> goods = mapper.queryGoods();
        for (Goods good : goods) {
            System.out.println(good.toString());
        }
    }

    @Test
    public void queryGoodsWithMap() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("minPrice",5000);
        params.put("maxPrice",10000);
        params.put("goodBrand","HP");
        GoodsDao mapper = MybatisUtil.getMapper(GoodsDao.class);
        List<Goods> goods = mapper.queryGoodsWithMap(params);
        for (Goods good : goods) {
            System.out.println(good);
        }

    }

    @Test
    public void queryGoodsWithParams() {
        MemberSearchCondition condition = new MemberSearchCondition(null, 5000, 12000, "Lenovo");
        GoodsDao mapper = MybatisUtil.getMapper(GoodsDao.class);
        List<Goods> goods = mapper.queryGoodsWithParams(condition);
        for (Goods good : goods) {
            System.out.println(good);
        }
    }

    @Test
    public void queryGoodsWithForEach() {
        GoodsDao mapper = MybatisUtil.getMapper(GoodsDao.class);
        List<String> brand = new ArrayList<>();
        brand.add("apple");
        brand.add("HP");
        List<Goods> goods = mapper.queryGoodsWithForEach(brand);
        for (Goods good : goods) {
            System.out.println(good.toString());
        }
    }

    @Test
    public void queryGoodsByKeys() {
        GoodsDao mapper = MybatisUtil.getMapper(GoodsDao.class);
        List<Goods> goods = mapper.queryGoodsByKeys("app");
        for (Goods good : goods) {
            System.out.println(good.toString());
        }
    }

    @Test
    public void updateGoods() {
    }
}