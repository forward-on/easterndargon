package com.ly.designpattern.builder;

import java.util.List;

/**
 * @Description:
 * @Date 2018-07-23 18:34
 * @Author ly
 */
public abstract class AbstractSportSeqBuilder {

    public abstract void setSequence(List<String> sequence);

    public abstract AbstractSport getSport();

}
