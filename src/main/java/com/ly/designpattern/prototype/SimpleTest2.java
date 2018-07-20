package com.ly.designpattern.prototype;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Description: 浅复制 测试
 * @Date 2018-07-20 10:50
 * @Author ly
 */
public class SimpleTest2 {

    private static final int SEND_PERSON_COUNT = 10000;

    public static void main(String[] args) {

        ThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("thread-%s").build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 30, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), threadFactory);

        SendSalary sendSalary = new SendSalary(new SendSalaryTemplate());
        sendSalary.setSignature("【中国银行】");//签名，固定
        sendSalary.setSendDyncmicContext("新的活动哦！");//动态内容，管理可动态输入
        Long phone = 13311112345L;
        List<Long> timeList = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < SEND_PERSON_COUNT; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    SendSalary sendSalaryClone = sendSalary.clone();
                    sendSalaryClone.setUserName("张三");
                    sendSalaryClone.setSalary(new Random().nextDouble() * 10000);
                    sendSalaryClone.setMobile((phone).toString());
                    sendSalaryClone.setMonth(1);
                    sendSms(sendSalaryClone);
                    System.out.println("thread pool size  = " + executor.getPoolSize());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    long end = System.currentTimeMillis();
                    timeList.add(end);
                }
            });
        }
        List<Long> collect = timeList.stream().sorted().collect(Collectors.toList());
        executor.shutdown();
        try {
            Thread.sleep(18000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("timelist.size= " + timeList.size());
        System.out.println(collect.get(0) - start);
        System.out.println(collect.get(collect.size() - 1) - start);
        System.out.println("cost:" + (System.currentTimeMillis() - start));

    }

    public static void sendSms(SendSalary sendSalary) {

//        System.out.println(sendSalary.getSendDyncmicContext()
//                + sendSalary.getUserName() + "，您好("
//                + sendSalary.getMobile() + ")，您于 "
//                + sendSalary.getMonth() + " 月 工资是 "
//                + sendSalary.getSalary() + "，请注意查收。"
//                + sendSalary.getSignature());
    }

}
