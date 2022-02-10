package com.marshio.mybatis.mapper;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author masuo
 * @data 27/1/2022 上午8:42
 * @Description 测试
 */
public class StudentMapperImplTest {

    @Test
    public void queryStudent() {
        StudentMapper studentMapper = new StudentMapperImpl();
        studentMapper.queryStudent("20173602");
    }
}