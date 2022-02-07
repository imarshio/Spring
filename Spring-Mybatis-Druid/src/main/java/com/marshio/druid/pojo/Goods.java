package com.marshio.druid.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 
 */
public class Goods implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private Integer gid;

    /**
     * 
     */
    private String goodName;

    /**
     * 
     */
    private Integer goodNum;

    /**
     * 
     */
    private Integer goodPrice;

    /**
     * 
     */
    private String goodBrand;

    /**
     * 
     */
    private String goodUnit;

    /**
     * 
     */
    private Timestamp goodShelfTime;

    /**
     * 
     */
    private String goodDesc;

    public Goods(Integer gid, String goodName, Integer goodNum, Integer goodPrice, String goodBrand, String goodUnit, Timestamp goodShelfTime, String goodDesc) {
        this.gid = gid;
        this.goodName = goodName;
        this.goodNum = goodNum;
        this.goodPrice = goodPrice;
        this.goodBrand = goodBrand;
        this.goodUnit = goodUnit;
        this.goodShelfTime = goodShelfTime;
        this.goodDesc = goodDesc;
    }

    /**
     * 
     */
    public Integer getGid() {
        return gid;
    }

    /**
     * 
     */
    public void setGid(Integer gid) {
        this.gid = gid;
    }

    /**
     * 
     */
    public String getGoodName() {
        return goodName;
    }

    /**
     * 
     */
    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    /**
     * 
     */
    public Integer getGoodNum() {
        return goodNum;
    }

    /**
     * 
     */
    public void setGoodNum(Integer goodNum) {
        this.goodNum = goodNum;
    }

    /**
     * 
     */
    public Integer getGoodPrice() {
        return goodPrice;
    }

    /**
     * 
     */
    public void setGoodPrice(Integer goodPrice) {
        this.goodPrice = goodPrice;
    }

    /**
     * 
     */
    public String getGoodBrand() {
        return goodBrand;
    }

    /**
     * 
     */
    public void setGoodBrand(String goodBrand) {
        this.goodBrand = goodBrand;
    }

    /**
     * 
     */
    public String getGoodUnit() {
        return goodUnit;
    }

    /**
     * 
     */
    public void setGoodUnit(String goodUnit) {
        this.goodUnit = goodUnit;
    }

    /**
     * 
     */
    public Date getGoodShelfTime() {
        return goodShelfTime;
    }

    /**
     * 
     */
    public void setGoodShelfTime(Timestamp goodShelfTime) {
        this.goodShelfTime = goodShelfTime;
    }

    /**
     * 
     */
    public String getGoodDesc() {
        return goodDesc;
    }

    /**
     * 
     */
    public void setGoodDesc(String goodDesc) {
        this.goodDesc = goodDesc;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Goods other = (Goods) that;
        return (this.getGid() == null ? other.getGid() == null : this.getGid().equals(other.getGid()))
            && (this.getGoodName() == null ? other.getGoodName() == null : this.getGoodName().equals(other.getGoodName()))
            && (this.getGoodNum() == null ? other.getGoodNum() == null : this.getGoodNum().equals(other.getGoodNum()))
            && (this.getGoodPrice() == null ? other.getGoodPrice() == null : this.getGoodPrice().equals(other.getGoodPrice()))
            && (this.getGoodBrand() == null ? other.getGoodBrand() == null : this.getGoodBrand().equals(other.getGoodBrand()))
            && (this.getGoodUnit() == null ? other.getGoodUnit() == null : this.getGoodUnit().equals(other.getGoodUnit()))
            && (this.getGoodShelfTime() == null ? other.getGoodShelfTime() == null : this.getGoodShelfTime().equals(other.getGoodShelfTime()))
            && (this.getGoodDesc() == null ? other.getGoodDesc() == null : this.getGoodDesc().equals(other.getGoodDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGid() == null) ? 0 : getGid().hashCode());
        result = prime * result + ((getGoodName() == null) ? 0 : getGoodName().hashCode());
        result = prime * result + ((getGoodNum() == null) ? 0 : getGoodNum().hashCode());
        result = prime * result + ((getGoodPrice() == null) ? 0 : getGoodPrice().hashCode());
        result = prime * result + ((getGoodBrand() == null) ? 0 : getGoodBrand().hashCode());
        result = prime * result + ((getGoodUnit() == null) ? 0 : getGoodUnit().hashCode());
        result = prime * result + ((getGoodShelfTime() == null) ? 0 : getGoodShelfTime().hashCode());
        result = prime * result + ((getGoodDesc() == null) ? 0 : getGoodDesc().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", gid=").append(gid);
        sb.append(", goodName=").append(goodName);
        sb.append(", goodNum=").append(goodNum);
        sb.append(", goodPrice=").append(goodPrice);
        sb.append(", goodBrand=").append(goodBrand);
        sb.append(", goodUnit=").append(goodUnit);
        sb.append(", goodShelfTime=").append(goodShelfTime);
        sb.append(", goodDesc=").append(goodDesc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}