package com.ly.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Date 2018-07-25 14:36
 * @Author ly
 */
public class ProxyHorse implements InvocationHandler {

    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("begin ...");
        Object obj = method.invoke(target, args);
        System.out.println("end ...");
        return obj;
    }
}
