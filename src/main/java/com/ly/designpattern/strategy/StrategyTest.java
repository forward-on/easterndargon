package com.ly.designpattern.strategy;

/**
 * @Description:
 * @Date 2018-07-20 18:24
 * @Author ly
 */
public class StrategyTest {

    public static void main(String[] args) {

        Travel travel = new Travel(new Train());
        travel.transfer();

    }

}
