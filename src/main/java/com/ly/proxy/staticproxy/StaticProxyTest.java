package com.ly.proxy.staticproxy;

/**
 * @Description:静态代理
 *                  代理对象 和 被代理对象 要实现同一个接口。
 *                  缺点：当有大量的类要被代理时，就会有大量的代理类；同时也不便于维护。
 * @Date 2018-07-25 14:38
 * @Author ly
 */
public class StaticProxyTest {

    public static void main(String[] args) {
        Horse horse = new Horse();
        ProxyHorse proxyHorse = new ProxyHorse(horse);
        proxyHorse.buyHorse();
    }

}
