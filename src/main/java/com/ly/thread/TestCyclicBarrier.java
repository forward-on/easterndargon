package com.ly.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author : ly.
 * @Date : 2018/5/15.
 */
public class TestCyclicBarrier {

    //由于设置了同步屏障，数量为2，所以要想代码能顺利执行完，线程数量需要为2的倍数，并且每执行两个线程都会执行 Inner 类的run方法
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Inner());

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {

                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(4444);
            }
        }).start();
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {

                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(11111);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {

                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(5555);
            }
        }).start();

        System.out.println(22222);
    }

    static class Inner implements Runnable {

        @Override
        public void run() {
            System.out.println(33333);
        }
    }

}
