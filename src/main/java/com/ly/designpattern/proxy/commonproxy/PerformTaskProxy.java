package com.ly.designpattern.proxy.commonproxy;

/**
 * @Description:
 * @Date 2018-08-03 16:56
 * @Author ly
 */
public class PerformTaskProxy implements ITask {

    private ITask task = null;

    public PerformTaskProxy(String name) {
        try {
            task = new PerformTask(this, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
