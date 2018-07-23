package com.ly.designpattern.builder;

/**
 * @Description:
 * @Date 2018-07-23 18:24
 * @Author ly
 */
public class GymSport extends AbstractSport {

    @Override
    protected void walk() {
        System.out.println("慢走...");
    }

    @Override
    protected void bike() {
        System.out.println("小自行车不动地骑...");
    }

    @Override
    protected void run() {
        System.out.println("跑步机上跑步...");
    }

    @Override
    protected void jump() {
        System.out.println("到处乱蹦...");
    }
}
