package com.marshio.pojo;

import java.util.Date;

/**
 * @author masuo
 * @data 11/1/2022 下午1:10
 * @Description User 实体
 */

public class User {

    private String uNum;
    private String uName;
    private int uAge;
    // 出生
    private Date uDate;

    @Override
    public String toString() {
        return "User{" +
                "uNum='" + uNum + '\'' +
                ", uName='" + uName + '\'' +
                ", uAge=" + uAge +
                ", uDate=" + uDate +
                '}';
    }

    public String getuNum() {
        return uNum;
    }

    public void setuNum(String uNum) {
        this.uNum = uNum;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public int getuAge() {
        return uAge;
    }

    public void setuAge(int uAge) {
        this.uAge = uAge;
    }

    public Date getuDate() {
        return uDate;
    }

    public void setuDate(Date uDate) {
        this.uDate = uDate;
    }
}
