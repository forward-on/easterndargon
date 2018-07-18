package com.ly.designpattern.factorymethod.product.impl;

import com.ly.designpattern.factorymethod.product.Human;

/**
 * @Description:
 * @Date 2018-07-18 16:36
 * @Author ly
 */
public class YellowHuman implements Human {

    @Override
    public void getColor() {
        System.out.println("I am yellow man");
    }

    @Override
    public void say() {
        System.out.println("你好， 我是黄种人...");
    }
}
