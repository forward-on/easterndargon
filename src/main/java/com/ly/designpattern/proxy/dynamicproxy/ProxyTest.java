package com.ly.designpattern.proxy.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * @Description: 动态代理
 * @Date 2018-08-03 16:51
 * @Author ly
 */
public class ProxyTest {

    public static void main(String[] args) {

        //法1
        PerformTask performTask = new PerformTask();
        MyInvocation myInvocation = new MyInvocation(performTask);
        ITask proxy = (ITask) Proxy.newProxyInstance(performTask.getClass().getClassLoader(),
                performTask.getClass().getInterfaces(),
                myInvocation);
        proxy.begin();
        proxy.run();
        proxy.end();

        System.out.println("============");

        //法2
        MyInvocation<ITask> iTaskMyInvocation = new MyInvocation<>(performTask);
        ITask task = iTaskMyInvocation.getProxy();
        task.begin();
        task.run();
        task.end();

        // null pointer exception
        performTask = null;
        MyInvocation<Object> objectMyInvocation = new MyInvocation<>(performTask);
        ITask iTask = objectMyInvocation.getProxy();
//        iTask.end();

    }

}
