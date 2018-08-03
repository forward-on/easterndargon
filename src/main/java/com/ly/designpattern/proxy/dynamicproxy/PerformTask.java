package com.ly.designpattern.proxy.dynamicproxy;

/**
 * @Description:
 * @Date 2018-08-03 16:56
 * @Author ly
 */
public class PerformTask implements ITask {

    @Override
    public void begin() {
        System.out.println("PerformTask begin...");
    }

    @Override
    public void run() {
        System.out.println("PerformTask run...");
    }

    @Override
    public void end() {
        System.out.println("PerformTask end...");
    }
}
