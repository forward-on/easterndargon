package com.ly.designpattern.proxy.boundproxy;

/**
 * @Description:
 * @Date 2018-08-03 16:56
 * @Author ly
 */
public class PerformTask implements ITask{

    private String name;
    private ITask taskProxy = null;

    public PerformTask(String name) {
        this.name = name;
    }

    @Override
    public void begin() {
        if (isProxy()) {
            System.out.println("PerformTask begin...");
        } else {
            System.out.println("代理走丢了...");
        }
    }

    @Override
    public void run() {
        if (isProxy()) {
            System.out.println("PerformTask run...");
        } else {
            System.out.println("代理走丢了...");
        }
    }

    @Override
    public void end() {
        if (isProxy()) {
            System.out.println("PerformTask end...");
        } else {
            System.out.println("代理走丢了...");
        }
    }

    @Override
    public ITask getProxy() {
        this.taskProxy = new PerformTaskProxy(this);
        return this.taskProxy;
    }

    private boolean isProxy(){
        if (taskProxy == null) {
            return false;
        } else {
            return true;
        }
    }

}
