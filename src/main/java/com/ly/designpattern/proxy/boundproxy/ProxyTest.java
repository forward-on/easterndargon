package com.ly.designpattern.proxy.boundproxy;

/**
 * @Description: 强制代理
 * @Date 2018-08-03 16:51
 * @Author ly
 */
public class ProxyTest {

    public static void main(String[] args) {

        PerformTask laowang = new PerformTask("laowang");
        laowang.begin();
        laowang.run();
        laowang.end();

        System.out.println("=======");

        PerformTaskProxy performTaskProxy = new PerformTaskProxy(laowang);
        performTaskProxy.begin();
        performTaskProxy.run();
        performTaskProxy.end();

        System.out.println("========");

        ITask proxy = laowang.getProxy();
        proxy.begin();
        proxy.run();
        proxy.end();

    }

}
