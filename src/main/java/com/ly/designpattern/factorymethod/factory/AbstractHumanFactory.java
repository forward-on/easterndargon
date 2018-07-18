package com.ly.designpattern.factorymethod.factory;

import com.ly.designpattern.factorymethod.product.Human;

/**
 * @Description:
 * @Date 2018-07-18 16:37
 * @Author ly
 */
public abstract class AbstractHumanFactory {

    /**
     * 创建传入的人种
     * @param c
     * @param <T>
     * @return
     */
    public abstract <T extends Human> T createHuman (Class<T> c);

}
