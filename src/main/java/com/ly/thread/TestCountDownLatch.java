package com.ly.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author : ly.
 * @Date : 2018/5/15.
 */
public class TestCountDownLatch {

    private static CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(11111);
            //用 countDown() 方法减一
            countDownLatch.countDown();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(22222);
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println(3333);
        }).start();

        try {
            //这个方法用于当 countDownLatch 的值大于0时 阻塞，等于0时放行
            countDownLatch.await();
            System.out.println("finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
