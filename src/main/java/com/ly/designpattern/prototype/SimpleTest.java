package com.ly.designpattern.prototype;

import java.util.Random;

/**
 * @Description: 浅复制 测试
 * @Date 2018-07-20 10:50
 * @Author ly
 */
public class SimpleTest {

    private static final int SEND_PERSON_COUNT = 10000;

    public static void main(String[] args) {

        SendSalary sendSalary = new SendSalary(new SendSalaryTemplate());
        sendSalary.setSignature("【中国银行】");//签名，固定
        sendSalary.setSendDyncmicContext("新的活动哦！");//动态内容，管理可动态输入
        Long phone = 13311112345L;
        int i = 1;
        long start = System.currentTimeMillis();
        while (i < SEND_PERSON_COUNT) {
            SendSalary sendSalaryClone = sendSalary.clone();
            sendSalaryClone.setUserName("张三" + i);
            sendSalaryClone.setSalary(new Random().nextDouble()*10000);
            sendSalaryClone.setMobile((phone++).toString());
            sendSalaryClone.setMonth(i);
            sendSms(sendSalaryClone);
            i++;
        }
        System.out.println("cost:" + (System.currentTimeMillis()-start));

    }

    public static void sendSms(SendSalary sendSalary){

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sendSalary.getSendDyncmicContext()
                + sendSalary.getUserName() + "，您好("
                + sendSalary.getMobile() + ")，您于 "
                + sendSalary.getMonth() + " 月 工资是 "
                + sendSalary.getSalary() + "，请注意查收。"
                + sendSalary.getSignature());
    }

}
