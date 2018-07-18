package com.ly.designpattern.factorymethod.factory;

import com.ly.designpattern.factorymethod.product.Human;

/**
 * @Description:
 * @Date 2018-07-18 16:39
 * @Author ly
 */
public class HumanFactory extends AbstractHumanFactory {

    @Override
    public <T extends Human> T createHuman(Class<T> c) {
        Human human = null;
        try {
            //利用反射创建传入的人种
            human = (Human) Class.forName(c.getName()).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return (T) human;
    }
}
