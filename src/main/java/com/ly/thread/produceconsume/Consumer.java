package com.ly.thread.produceconsume;

import java.util.concurrent.BlockingQueue;

/**
 * @Description:
 * @Date 2018-07-24 10:20
 * @Author ly
 */
//消费者
public class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue q){
        this.queue=q;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(2000);//模拟耗时
                consume(queue.take());
            }catch (InterruptedException e){

            }

        }
    }

    private void consume(Integer n) {
        System.out.println("Thread:" + Thread.currentThread().getId() + " consume:" + n);

    }
}
