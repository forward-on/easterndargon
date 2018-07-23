package com.ly.designpattern.cor;

/**
 * @Description:
 * @Date 2018-07-23 16:31
 * @Author ly
 */
public class PosManager extends AbstractPosition {

    public PosManager() {
        super(AbstractPosition.TYPE_MANAGER);
    }

    @Override
    protected void response() {
        System.out.println("OK, manager agree ...");
    }
}
