package com.ly.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Date 2018-07-25 15:45
 * @Author ly
 */
public class CglibProxy implements MethodInterceptor {

    private Object target;

    public CglibProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("begin ...");
        Object obj = method.invoke(target, objects);
        System.out.println("end ...");
        return obj;
    }

    /**
     * 获取代理对象
     */
    public Object getProxyInstance(){
        //工具类
        Enhancer enhancer = new Enhancer();
        //设置父类（即为被代理类）
        enhancer.setSuperclass(target.getClass());
        //设置回调函数（会回调本类中的 intercept 方法）
        enhancer.setCallback(this);
        //创建子类（即为代理类）
        return enhancer.create();
    }

}
