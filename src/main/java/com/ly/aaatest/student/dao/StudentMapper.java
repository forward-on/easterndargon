package com.ly.aaatest.student.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.ly.aaatest.student.model.Student;

public interface StudentMapper {

    int insert(@Param("pojo") Student pojo);

    int insertList(@Param("pojos") List< Student> pojo);

    List<Student> select(@Param("pojo") Student pojo);

    int update(@Param("pojo") Student pojo);

}
