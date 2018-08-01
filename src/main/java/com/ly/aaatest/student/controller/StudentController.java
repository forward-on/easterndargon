package com.ly.aaatest.student.controller;

import com.alibaba.fastjson.JSONObject;
import com.ly.aaatest.student.model.Student;
import com.ly.aaatest.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Description:
 * @Date 2018-07-24 16:40
 * @Author ly
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public Object get(){
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Student post(@RequestBody JSONObject jsonObject) {

        Student student = new Student();
        student.setAge(jsonObject.getInteger("age"));
        student.setScore(jsonObject.getInteger("score"));
        student.setName(jsonObject.getString("name"));
        student.setAddress(jsonObject.getString("address"));
        Date now = new Date();
        student.setCreateTime(now);
        student.setUpdateTime(now);
        studentService.insert(student);
        return student;
    }

}
