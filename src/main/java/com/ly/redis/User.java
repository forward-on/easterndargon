package com.ly.redis;

import java.io.Serializable;

/**
 * @author : ly.
 * @Date : 2018/5/23.
 */
public class User implements Serializable{

    private static final long serialVersionUID = -19942357521226715L;
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
