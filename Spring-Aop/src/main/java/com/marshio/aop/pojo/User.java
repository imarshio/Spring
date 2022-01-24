package com.marshio.aop.pojo;

import org.springframework.stereotype.Component;

/**
 * @author masuo
 * @data 24/1/2022 上午10:24
 * @Description 注解版
 */
@Component(value = "stu")
public class User {
    private int uid;
    private String userNum;
    private String userName;

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", userNum='" + userNum + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
