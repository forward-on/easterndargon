package com.ly.designpattern.factorymethod.product.impl;

import com.ly.designpattern.factorymethod.product.Human;

/**
 * @Description:
 * @Date 2018-07-18 16:33
 * @Author ly
 */
public class WhiteHuman implements Human {

    @Override
    public void getColor() {
        System.out.println("I am white human...");
    }

    @Override
    public void say() {
        System.out.println("hello world, I say English");
    }
}
