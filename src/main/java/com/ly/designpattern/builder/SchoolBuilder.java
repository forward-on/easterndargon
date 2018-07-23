package com.ly.designpattern.builder;

import java.util.List;

/**
 * @Description:
 * @Date 2018-07-23 18:38
 * @Author ly
 */
public class SchoolBuilder extends AbstractSportSeqBuilder {

    private SchoolSport schoolSport = new SchoolSport();

    @Override
    public void setSequence(List<String> sequence) {
        this.schoolSport.setSportSeqList(sequence);
    }

    @Override
    public AbstractSport getSport() {
        return this.schoolSport;
    }
}
