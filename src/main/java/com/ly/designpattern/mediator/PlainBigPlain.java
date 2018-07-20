package com.ly.designpattern.mediator;

/**
 * @Description:
 * @Date 2018-07-20 15:06
 * @Author ly
 */
public class PlainBigPlain extends AbstractColleague{

    public PlainBigPlain(AbstractMediator abstractMediator) {
        super(abstractMediator);
    }

    public void selfMethod2() {

    }

    public void depMethod2() {
        super.mediator.doSomething2();
    }

}
