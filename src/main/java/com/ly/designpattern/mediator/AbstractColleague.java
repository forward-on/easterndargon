package com.ly.designpattern.mediator;

/**
 * @Description: 抽象同事类
 * @Date 2018-07-20 15:06
 * @Author ly
 */
public abstract class AbstractColleague {

    protected AbstractMediator mediator;

    // 使用构造器注入中介者  原因： 所有的同事类都需要中介者！
    public AbstractColleague(AbstractMediator abstractMediator) {
        this.mediator = abstractMediator;
    }

}
