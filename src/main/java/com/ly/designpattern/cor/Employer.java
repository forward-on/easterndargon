package com.ly.designpattern.cor;

/**
 * @Description: 雇员
 * @Date 2018-07-23 16:02
 * @Author ly
 */
public class Employer implements IRequester {

    private static final Integer TYPE_EMPLOYER = 0;
    private String request = "";

    public Employer() {
        this.request = "employer quit ...";
        System.out.println("employer quit ...");
    }

    @Override
    public int getType() {
        return TYPE_EMPLOYER;
    }

    @Override
    public String getRequest() {
        return this.request;
    }
}
