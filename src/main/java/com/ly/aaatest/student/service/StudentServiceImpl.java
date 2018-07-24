package com.ly.aaatest.student.service;

import com.ly.aaatest.student.dao.StudentMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.ly.aaatest.student.model.Student;

@Service
public class StudentServiceImpl implements StudentService{

    @Resource
    private StudentMapper studentMapper;

    @Override
    public int insert(Student pojo){
        return studentMapper.insert(pojo);
    }

    @Override
    public int insertList(List< Student> pojos){
        return studentMapper.insertList(pojos);
    }

    @Override
    public List<Student> select(Student pojo){
        return studentMapper.select(pojo);
    }

    @Override
    public int update(Student pojo){
        return studentMapper.update(pojo);
    }


}
