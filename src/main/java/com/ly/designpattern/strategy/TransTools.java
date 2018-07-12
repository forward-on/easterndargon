package com.ly.designpattern.strategy;

/**
 * @Description:
 * @Date 2018-07-03 20:39
 * @Author ly
 */
public abstract class TransTools {

    public abstract void goTool();

    public TransTools() {
    }

    public void goSay() {
        System.out.println("乘坐xxx");
    }
}
