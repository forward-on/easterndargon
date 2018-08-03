package com.ly.designpattern.proxy.boundproxy;

/**
 * @Description: 强制代理：通过被代理类找代理类
 * @Date 2018-08-03 16:54
 * @Author ly
 */
public interface ITask {

    void begin();

    void run();

    void end();

    /**
     * 增加一个获取代理的方法
     * @return
     */
    ITask getProxy();

}
