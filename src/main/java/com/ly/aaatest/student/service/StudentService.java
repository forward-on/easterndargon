package com.ly.aaatest.student.service;

import com.ly.aaatest.student.model.Student;
import com.ly.database.DataSource;

import java.util.List;

/**
 * @Description:
 * @Date 2018-07-24 17:55
 * @Author ly
 */
public interface StudentService {

    @DataSource(value = "master")
    int insert(Student pojo);

    int insertList(List< Student> pojos);

    List<Student> select(Student pojo);

    int update(Student pojo);

}
