package com.ly.designpattern.prototype;

import java.util.ArrayList;

/**
 * @Description: 原型模式
 *                  不通过new产生新对象，而是通过复制产生新对象的模式。
 *                  实现 Cloneable 接口，重写 clone 方法。
 *                  注意：
 *                      1、使用 clone 方法得到的对象，是不会执行构造方法的。
 *                      2、复制分为两种：浅拷贝和深拷贝
 *                          浅拷贝：只拷贝本对象，对象内部的数组、引用对象（必须满足两个条件：一是类的成员变量而不是方法内变量；二是必须是可变的引用对象，而不是原始类型或者不可不对象）
 *                                  等都不拷贝，仍然指向原对象，也就是说，他们共享这部分对象。
 *                                  他们会拷贝基本数据类型和string对象。
 *                          深拷贝：全部拷贝。
 *                          浅拷贝和深拷贝不建议混合使用，特别是在有类的继承的情况下。
 *                      3、如果要使用 clone 方法，就不要在类的成员变量上加 final 关键字。会引起冲突。
 * @Date 2018-07-20 10:37
 * @Author ly
 */
public class SendSalary implements Cloneable{

    private double salary;
    private String userName;
    private int month;
    private String signature;
    private String mobile;
    private String sendDyncmicContext;
    private ArrayList<String> list = new ArrayList<>();

    public SendSalary(SendSalaryTemplate sendSalaryTemplate) {
        this.sendDyncmicContext = sendSalaryTemplate.getDynamicContext();
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSendDyncmicContext() {
        return sendDyncmicContext;
    }

    public void setSendDyncmicContext(String sendDyncmicContext) {
        this.sendDyncmicContext = sendDyncmicContext;
    }

    @Override
    protected SendSalary clone() {
        SendSalary sendSalary = null;
        try {
            sendSalary = (SendSalary) super.clone();
            //深拷贝时加上这行代码：
            // sendSalary.list = (ArrayList<String>) this.list.clone();
            // 深拷贝另一种实现方式： 自己写二进制流操作对象进行拷贝
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return sendSalary;
    }
}
