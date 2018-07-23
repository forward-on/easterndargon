package com.ly.designpattern.builder;

/**
 * @Description:
 * @Date 2018-07-23 18:23
 * @Author ly
 */
public class SchoolSport extends AbstractSport {
    @Override
    protected void walk() {
        System.out.println("操场竞走...");
    }

    @Override
    protected void bike() {
        System.out.println("操场骑车...");
    }

    @Override
    protected void run() {
        System.out.println("操场转圈跑步...");
    }

    @Override
    protected void jump() {
        System.out.println("操场蹦蹦跳跳...");
    }
}
