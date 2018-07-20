package com.ly.designpattern.strategy;

/**
 * @Description:策略模式
 * @Date 2018-07-03 20:24
 * @Author ly
 */
public class Travel {

    private TransTools transTools;

    // 以构造器的方式传入 交通方式
    public Travel(TransTools transTools) {
        this.transTools = transTools;
    }

    public void from(){
        System.out.println("始发地：");
    }

    public void to() {
        System.out.println("目的地：");
    }

    public void transfer() {
        this.transTools.goTool();
        this.transTools.cost();
    }

}
