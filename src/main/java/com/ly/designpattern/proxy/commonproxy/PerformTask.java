package com.ly.designpattern.proxy.commonproxy;

/**
 * @Description:
 * @Date 2018-08-03 16:56
 * @Author ly
 */
public class PerformTask implements ITask{

    private String name;

    public PerformTask(ITask iTask, String name) throws Exception {
        // 可以限制指定的代理访问
        if (iTask == null) {
            throw new Exception("请指定代理！");
        } else {
            this.name = name;
        }
    }

    @Override
    public void begin() {
        System.out.println("PerformTask begin...");
    }

    @Override
    public void run() {
        System.out.println("PerformTask run...");
    }

    @Override
    public void end() {
        System.out.println("PerformTask end...");
    }
}
