package com.marshio.druid.dao.conditions;

/**
 * @author masuo
 * @data 27/1/2022 下午1:27
 * @Description 搜索条件
 */

public class MemberSearchCondition {
    private String goodName;
    private int minPrice;
    private int maxPrice;
    private String goodBrand;

    public MemberSearchCondition(String goodName, int minPrice, int maxPrice, String goodBrand) {
        this.goodName = goodName;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.goodBrand = goodBrand;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getGoodBrand() {
        return goodBrand;
    }

    public void setGoodBrand(String goodBrand) {
        this.goodBrand = goodBrand;
    }
}
