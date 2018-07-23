package com.ly.designpattern.cor;

/**
 * @Description: 使多个对象都有机会处理请求，从而避免了请求的发送者和接受者之间的耦合关系。将这些对象串成一条链并沿着这条链传递请求，直到有对象处理它为止。
 * @Date 2018-07-23 15:46
 * @Author ly
 */
public abstract class AbstractPosition {

    public static final Integer TYPE_EMPLOYER = 0;
    public static final Integer TYPE_MANAGER = 1;
    public static final Integer TYPE_ADMINISTRATION = 2;
    public static final Integer TYPE_FINANCE = 3;
    public static final Integer TYPE_BOSS = 4;

    private Integer type;
    private AbstractPosition nextAbstractPosition;

    public AbstractPosition(Integer type) {
        this.type = type;
    }

    public void setNextAbstractPosition(AbstractPosition nextAbstractPosition) {
        this.nextAbstractPosition = nextAbstractPosition;
    }

    public final void handleMessage(IRequester iRequester) {
        if (iRequester.getType() < this.type) {
            // deal
            this.response();
        } else {
            if (this.nextAbstractPosition != null) {
                this.nextAbstractPosition.handleMessage(iRequester);
            } else {
                System.out.println("no have dealer, exit...");
            }
        }
    }

    protected abstract void response ();

}
