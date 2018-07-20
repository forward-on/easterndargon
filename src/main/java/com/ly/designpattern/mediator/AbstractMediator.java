package com.ly.designpattern.mediator;

/**
 * @Description: 中介者
 * @Date 2018-07-20 15:08
 * @Author ly
 */
public abstract class AbstractMediator {

    // 假设只有一条航道，两架飞机争
    private PlainJianShi plainJianShi;
    private PlainBigPlain plainBigPlain;

    // 使用getter、setter注入同事类  原因是：中介者不一定需要所有的同事类（可能只需要部分）
    public PlainJianShi getPlainJianShi() {
        return plainJianShi;
    }

    public void setPlainJianShi(PlainJianShi plainJianShi) {
        this.plainJianShi = plainJianShi;
    }

    public PlainBigPlain getPlainBigPlain() {
        return plainBigPlain;
    }

    public void setPlainBigPlain(PlainBigPlain plainBigPlain) {
        this.plainBigPlain = plainBigPlain;
    }

    public abstract void doSomething1();

    public abstract void doSomething2();

}
