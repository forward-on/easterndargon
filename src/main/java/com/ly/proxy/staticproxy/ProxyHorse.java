package com.ly.proxy.staticproxy;

/**
 * @Description:
 * @Date 2018-07-25 14:36
 * @Author ly
 */
public class ProxyHorse implements IHorse {

    private Horse horse;

    public ProxyHorse(Horse horse) {
        this.horse = horse;
    }

    @Override
    public void buyHorse() {
        System.out.println("begin ...");
        horse.buyHorse();
        System.out.println("end ...");
    }
}
