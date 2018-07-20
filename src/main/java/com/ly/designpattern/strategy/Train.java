package com.ly.designpattern.strategy;

/**
 * @Description:
 * @Date 2018-07-20 15:40
 * @Author ly
 */
public class Train implements TransTools {

    @Override
    public void goTool() {
        System.out.println("I am by train...");
    }

    @Override
    public void cost() {
        System.out.println("I cost 110 dollars");
    }
}
