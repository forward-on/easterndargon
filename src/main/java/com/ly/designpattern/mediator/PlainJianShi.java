package com.ly.designpattern.mediator;

/**
 * @Description:
 * @Date 2018-07-20 15:06
 * @Author ly
 */
public class PlainJianShi extends AbstractColleague {

    public PlainJianShi(AbstractMediator abstractMediator) {
        super(abstractMediator);
    }

    //自有方法，自己处理
    public void selfMethod1() {

    }

    //依赖方法，交给中介者处理
    public void depMethod1() {
        super.mediator.doSomething1();
    }

}
