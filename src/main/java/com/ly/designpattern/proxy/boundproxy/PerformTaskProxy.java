package com.ly.designpattern.proxy.boundproxy;

/**
 * @Description:
 * @Date 2018-08-03 16:56
 * @Author ly
 */
public class PerformTaskProxy implements ITask {

    private ITask task = null;

    public PerformTaskProxy(ITask task) {
        this.task = task;
    }

    @Override
    public void begin() {
        this.task.begin();
    }

    @Override
    public void run() {
        this.task.run();
    }

    @Override
    public void end() {
        this.task.end();
    }

    @Override
    public ITask getProxy() {
        return this;
    }
}
