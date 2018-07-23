package com.ly.designpattern.cor;

/**
 * @Description:
 * @Date 2018-07-23 16:36
 * @Author ly
 */
public class PosAdministration extends AbstractPosition {

    public PosAdministration() {
        super(AbstractPosition.TYPE_ADMINISTRATION);
    }

    @Override
    protected void response() {
        System.out.println("OK, administration agree ...");
    }
}
