package com.ly.designpattern.strategy;

/**
 * @Description:
 * @Date 2018-07-20 15:42
 * @Author ly
 */
public class Plain implements TransTools {
    @Override
    public void goTool() {
        System.out.println("I am by plain...");
    }

    @Override
    public void cost() {
        System.out.println("I cost 1000 dollars");
    }
}
