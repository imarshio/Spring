package com.marshio.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author masuo
 * @date 2022/01/15/ 下午3:25
 * @description
 */

public class Student {

    private int id;
    private String stuNum;
    private String stuName;
    private String stuSex;
    private int stuAge;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuNum='" + stuNum + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuSex='" + stuSex + '\'' +
                ", stuAge=" + stuAge +
                '}';
    }

    public Student() {
    }

    public Student(String stuNum, String stuName, String stuSex, int stuAge) {
        this.stuNum = stuNum;
        this.stuName = stuName;
        this.stuSex = stuSex;
        this.stuAge = stuAge;
    }

    public Student(int id, String stuNum, String stuName, String stuSex, int stuAge) {
        this.id = id;
        this.stuNum = stuNum;
        this.stuName = stuName;
        this.stuSex = stuSex;
        this.stuAge = stuAge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }
}
