package com.ly.designpattern.mediator;

/**
 * @Description: 中介者模式（调停者模式）
 *                  用一个中介对象封装一系列的对象交互，中介者使得各对象不需要显式的相互作用，使他们低耦合，并且可以独立改变他们之间的交互
 *                  优点：
 *                      降低对象之间的耦合，把原有的一对多的依赖改为一对一的依赖
 *                  缺点：
 *                      中介者类可能会膨胀的很大，而且逻辑复杂
 *                  使用场景：
 *                      适用于多个对象之间紧密耦合的场景，紧密耦合的条件是：在类图中出现了蜘蛛网状结构。这是使用中介者模式有利于将其改为星型结构
 *                      机场调度中心、MVC框架、媒体网关、房产中介
 * @Date 2018-07-20 15:03
 * @Author ly
 */
public class PlainScheduleCenterAbstractMediator extends AbstractMediator {

    @Override
    public void doSomething1() {

    }

    @Override
    public void doSomething2() {

    }
}
