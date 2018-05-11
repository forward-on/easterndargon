package com.ly.threadpool;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : ly.
 * @Date : 2018/5/11.
 */
public class TestPool {

    private static ThreadPoolExecutor executor;

    public static void main(String[] args) {
        ThreadFactory factory = new BasicThreadFactory.Builder().namingPattern("thread-pool-%d").build();
        LinkedBlockingDeque<Runnable> queue = new LinkedBlockingDeque<>(4);
        executor = new ThreadPoolExecutor(3, 6, 3, TimeUnit.MICROSECONDS, queue, factory);

        try {
            for (int i = 0; i < 100; i++) {
                executor.execute(myTask());
                System.out.println(Thread.currentThread().getName() + " **** " + "第" + (i + 1) + "次");
            }
        } finally {
            executor.shutdown();
        }
    }

    private static Runnable myTask() {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("线程池中的线程数量：" + executor.getPoolSize());
                System.out.println("队列中任务数量：" + executor.getQueue().size());
            }
        };
    }

}
