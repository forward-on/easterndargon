package com.ly.designpattern.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Date 2018-08-03 17:50
 * @Author ly
 */
public class MyInvocation<T> implements InvocationHandler {

    private ITask proxyObj;

    public MyInvocation(ITask proxyObj) {
        this.proxyObj = proxyObj;
    }

    /**
     * 法2
     * @param <T>
     * @return
     */
    public <T> T getProxy(){
        if (this.proxyObj == null) {
            return null;
        }
        ClassLoader proxyedClassLoader = this.proxyObj.getClass().getClassLoader();
        Class<?>[] interfaces = this.proxyObj.getClass().getInterfaces();
        return (T) Proxy.newProxyInstance(proxyedClassLoader, interfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxyObj, args);
    }
}
