package com.ly.innerclass;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : ly.
 * @Date : 2018/5/11.
 */
public class School {

    private String name;
    private Integer grade;
    private static double area;

    //成员内部类
    class Finance {
        private double money;
        public void display(){
            System.out.println(name + "学校的" + grade + "年级的费用是" + money + "元");
        }
    }

    public String getClassName (Integer grade) {
        //方法内部类
        class Class {
            private Map<Integer, String> classMap = new HashMap<>();
            private String name;
            {
                classMap.put(1, "一年级");
                classMap.put(2, "二年级");
                classMap.put(3, "三年级");
                classMap.put(4, "四年级");
                classMap.put(5, "五年级");
                classMap.put(6, "六年级");
            }
            private String getNameByGrade(Integer grade) {
                return classMap.get(grade);
            }
            private void printInfo() {
                //访问外部类的同名属性，使用 outerClassName.this.filed
                System.out.println("schoolName=" + School.this.name + ",className=" + this.name);
            }
            public String getName() {
                return name;
            }
            public void setName(String name) {
                this.name = name;
            }
        }
        Class aClass = new Class();
        String name = aClass.getNameByGrade(grade);
        aClass.setName(name);
        aClass.printInfo();
        return name;
    }

    //静态内部类
    static class Area {
        public void printArea(){
            //此处不能使用外部类的非静态属性
            System.out.println("area=" + area);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public static void main(String[] args) {
        School school = new School();
        school.setName("高级中学");
        String className = school.getClassName(3);

        //Area可以直接创建对象
        new Area();

    }


}
