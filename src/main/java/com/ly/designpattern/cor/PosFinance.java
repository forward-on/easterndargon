package com.ly.designpattern.cor;

/**
 * @Description:
 * @Date 2018-07-23 16:37
 * @Author ly
 */
public class PosFinance extends AbstractPosition {

    public PosFinance() {
        super(AbstractPosition.TYPE_FINANCE);
    }

    @Override
    protected void response() {
        System.out.println("OK, finance agree ...");
    }
}
