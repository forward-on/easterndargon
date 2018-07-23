package com.ly.designpattern.cor;

/**
 * @Description: 管理者
 * @Date 2018-07-23 16:11
 * @Author ly
 */
public class Manager implements IRequester{

    private static final Integer TYPE_MANAGER = 1;
    private String request = "";

    public Manager() {
        this.request = "manager request quit ...";
        System.out.println("manager quit ...");
    }

    @Override
    public int getType() {
        return TYPE_MANAGER;
    }

    @Override
    public String getRequest() {
        return this.request;
    }
}
