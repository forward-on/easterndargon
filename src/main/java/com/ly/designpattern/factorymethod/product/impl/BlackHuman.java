package com.ly.designpattern.factorymethod.product.impl;

import com.ly.designpattern.factorymethod.product.Human;

/**
 * @Description:
 * @Date 2018-07-18 16:35
 * @Author ly
 */
public class BlackHuman implements Human {

    @Override
    public void getColor() {
        System.out.println("I am black man");
    }

    @Override
    public void say() {
        System.out.println("hello, I say balabala...");
    }
}
