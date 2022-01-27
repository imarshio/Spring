package com.marshio.mybatis.mapper;

import com.marshio.mybatis.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author masuo
 * @data 25/1/2022 下午4:46
 * @Description 注解完成XML配置功能
 */
@Repository
@Mapper
public interface StudentMapper {

    @Select("select * from db_student where stu_num = #{stuNum}")
    Student queryStudent(String stuNum);
}
