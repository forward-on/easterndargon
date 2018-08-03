package com.ly.designpattern.proxy.commonproxy;

/**
 * @Description: 普通代理
 * @Date 2018-08-03 16:51
 * @Author ly
 */
public class ProxyTest {

    public static void main(String[] args) {

        PerformTaskProxy proxy = new PerformTaskProxy("laozhang");
        proxy.begin();
        proxy.run();
        proxy.end();

        System.out.println("============");



    }

}
