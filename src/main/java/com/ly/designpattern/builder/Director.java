package com.ly.designpattern.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 在导演层加上所有顺序，然后调用builder层设置顺序，返回对象
 * @Date 2018-07-23 18:52
 * @Author ly
 */
public class Director {

    private List<String> seqList = new ArrayList<>();
    private SchoolBuilder schoolBuilder = new SchoolBuilder();
    private GymBuilder gymBuilder = new GymBuilder();

    public SchoolSport createSchoolA() {
        this.seqList.clear();
        seqList.add("walk");
        seqList.add("jump");
        seqList.add("bike");
        seqList.add("run");
        this.schoolBuilder.setSequence(seqList);
        return (SchoolSport) this.schoolBuilder.getSport();
    }

    public SchoolSport createSchoolB() {
        this.seqList.clear();
        seqList.add("run");
        seqList.add("walk");
        seqList.add("bike");
        seqList.add("jump");
        this.schoolBuilder.setSequence(seqList);
        return (SchoolSport) this.schoolBuilder.getSport();
    }

    public GymSport createGymC() {
        this.seqList.clear();
        seqList.add("run");
        seqList.add("bike");
        seqList.add("walk");
        seqList.add("jump");
        this.gymBuilder.setSequence(seqList);
        return (GymSport) this.gymBuilder.getSport();
    }

    public GymSport createGymD() {
        this.seqList.clear();
        seqList.add("run");
        seqList.add("walk");
        seqList.add("jump");
        seqList.add("bike");
        this.gymBuilder.setSequence(seqList);
        return (GymSport) this.gymBuilder.getSport();
    }

}
