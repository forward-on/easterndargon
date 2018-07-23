package com.ly.designpattern.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 建造者模式：关注 类型 和 顺序！这是与工厂方法模式最大的不同。
 * @Date 2018-07-23 18:19
 * @Author ly
 */
public class BuilderTest {

    public static void main(String[] args) {

        /*List<String> seqList = new ArrayList<>();
        seqList.add("walk");
        seqList.add("jump");
        seqList.add("bike");
        seqList.add("run");
        SchoolBuilder schoolBuilder = new SchoolBuilder();
        schoolBuilder.setSequence(seqList);
        AbstractSport sport = schoolBuilder.getSport();
        sport.go();
        System.out.println("=======");
        GymBuilder gymBuilder = new GymBuilder();
        gymBuilder.setSequence(seqList);
        gymBuilder.getSport().go();*/


        System.out.println("============**************=============");
        Director director = new Director();
        director.createSchoolA().go();
        director.createSchoolB().go();
        director.createGymC().go();
        director.createGymD().go();

    }

}
