package com.marshio.mybatis.mapper;

import com.marshio.mybatis.pojo.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import static org.junit.Assert.*;

/**
 * @author masuo
 * @data 26/1/2022 下午4:12
 * @Description 测试
 */
@Controller
public class StudentMapperTest {

    @Autowired
    StudentMapper studentMapper;
    @Test
    public void queryStudent() {
        Student student = studentMapper.queryStudent("20223607");
        System.out.println(student);
    }
}