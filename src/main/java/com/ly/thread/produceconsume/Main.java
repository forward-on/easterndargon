package com.ly.thread.produceconsume;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description: 生产者与消费者
 * @Date 2018-07-24 10:21
 * @Author ly
 */
public class Main {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(100);
        Producer p=new Producer(queue);
        Consumer c1=new Consumer(queue);
        Consumer c2=new Consumer(queue);

        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
    }
}
