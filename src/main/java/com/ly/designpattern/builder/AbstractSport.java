package com.ly.designpattern.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Date 2018-07-23 18:21
 * @Author ly
 */
public abstract class AbstractSport {

    private List<String> sportSeqList = new ArrayList<>();

    public final void setSportSeqList(List<String> sportSeqList) {
        this.sportSeqList = sportSeqList;
    }

    protected abstract void walk();

    protected abstract void bike();

    protected abstract void run();

    protected abstract void jump();

    public final void go() {
        for (int i = 0; i < sportSeqList.size(); i++) {
            String str = this.sportSeqList.get(i);
            if ("walk".equalsIgnoreCase(str)) {
                this.walk();
            } else if ("bike".equalsIgnoreCase(str)) {
                this.bike();
            } else if ("run".equalsIgnoreCase(str)) {
                this.run();
            } else if ("jump".equalsIgnoreCase(str)) {
                this.jump();
            } else {
                System.out.println("no have this sport");
            }
        }
    }

}
