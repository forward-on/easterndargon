package com.ly.designpattern.factorymethod;

import com.ly.designpattern.factorymethod.factory.AbstractHumanFactory;
import com.ly.designpattern.factorymethod.factory.HumanFactory;
import com.ly.designpattern.factorymethod.product.impl.BlackHuman;
import com.ly.designpattern.factorymethod.product.impl.WhiteHuman;
import com.ly.designpattern.factorymethod.product.impl.YellowHuman;

/**
 * @Description:
 * @Date 2018-07-18 16:46
 * @Author ly
 */
public class FactoryTest {

    public static void main(String[] args) {

        // 工厂方法模式
        AbstractHumanFactory humanFactory = new HumanFactory();
        BlackHuman blackHuman = humanFactory.createHuman(BlackHuman.class);
        blackHuman.getColor();
        blackHuman.say();
        WhiteHuman whiteHuman = humanFactory.createHuman(WhiteHuman.class);
        whiteHuman.getColor();
        whiteHuman.say();
        YellowHuman yellowHuman = humanFactory.createHuman(YellowHuman.class);
        yellowHuman.getColor();
        yellowHuman.say();

    }


    /**
     * 简单工厂模式：
     *      去掉 AbstractHumanFactory， 直接将 HumanFactory 中的 createHuman 方法改为静态方法。
     *      优点： 简单
     *      缺点： 不便扩展
     */


    /**
     * 多工厂模式：
     *      将 HumanFactory 去掉，改为 BlackHumanFactory,WhiteHumanFactory,YellowHumanFactory ，各司其职
     *      优点： 当创建的人种对象初始化较复杂时，可以直接使用指定的工厂类创建人种，使得结构更清晰
     *      缺点： 不易于扩展，每创建一个人种类都要创建一个相对应的工厂类
     *      我们可以加一个协调类，对工厂类进行封装，统一处理，提供统一访问接口。
     */


    /**
     * 使用工厂方法替换单例：
     *      1、将要单例的类的构造器私有；
     *      2、利用反射生成单例对象
     */


    /**
     * 延迟初始化
     */

}
