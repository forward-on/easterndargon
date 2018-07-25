package com.ly.proxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * @Description:动态代理
 *                  1、代理对象不要实现和被代理对象同样的接口，而是实现 InvocationHandler ，重写 invoke 方法。
 *                  2、被代理对象一定要实现接口，否则不能使用动态代理
 * @Date 2018-07-25 14:38
 * @Author ly
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        //被代理对象
        Horse horse = new Horse();
        //代理对象
        ProxyHorse proxyHorse = new ProxyHorse();
        //设置代理对象的代理目标
        proxyHorse.setTarget(horse);
        //使用 Proxy.newProxyInstance 创建代理类
        IHorse proxy = (IHorse) Proxy.newProxyInstance(
                horse.getClass().getClassLoader(),
                horse.getClass().getInterfaces(),
                proxyHorse
        );

        proxy.buyHorse();

    }

}
