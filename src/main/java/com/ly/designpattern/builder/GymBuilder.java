package com.ly.designpattern.builder;

import java.util.List;

/**
 * @Description:
 * @Date 2018-07-23 18:50
 * @Author ly
 */
public class GymBuilder extends AbstractSportSeqBuilder {

    private GymSport gymSport = new GymSport();

    @Override
    public void setSequence(List<String> sequence) {
        this.gymSport.setSportSeqList(sequence);
    }

    @Override
    public AbstractSport getSport() {
        return this.gymSport;
    }
}
