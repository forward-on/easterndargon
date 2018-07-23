package com.ly.designpattern.cor;

/**
 * @Description:
 * @Date 2018-07-23 17:29
 * @Author ly
 */
public class Administrator implements IRequester {

    private String request;

    public Administrator(){
        this.request = "administrator quit ...";
        System.out.println("administrator quit ...");
    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public String getRequest() {
        return this.request;
    }
}
