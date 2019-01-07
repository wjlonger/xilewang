package com.wuwei.consumer.wechat.utils;

import java.util.ArrayList;
import java.util.List;

//@Configuration
public class RatioCalculator {

    /**
     * 一次助力最低比例
     */
    //@Value("${assistance.once.lowest.ratio}")
    private int assistanceOnceLowestRatio;

    /**
     * 助力最高比例与平均值的比
     */
    //@Value("${assistance.once.avg.ratio}")
    private double assistanceOnceAvgRatio;

    /**
     * 助力人数
     */
    //@Value("${assistance.people.number}")
    private int assistancePeopleNumber;

    private int randomRatio(int allRatio,int minRatio,int maxRatio,int count){
        if (count == 1) {
            return allRatio;
        }
        if (minRatio == maxRatio) {
            return minRatio;
        }
        int max = Math.min(allRatio,maxRatio);
        int maxY = allRatio - (count-1) * minRatio;
        int minY = allRatio - (count-1) * maxRatio;
        int min = Math.max(minRatio,minY);
        max = Math.min(max,maxY);
        return (int)Math.rint(Math.random()*(max-min) +min);
    }

    public List<Integer> getRatio(int assistanceMaxRatio){
        List<Integer> ratios = new ArrayList<>();
        int max = (int)(assistanceMaxRatio * assistanceOnceAvgRatio / assistancePeopleNumber);
        max = Math.min(max,assistanceMaxRatio);
        for(int i=0; i<assistancePeopleNumber; i++){
            int ratio = randomRatio(assistanceMaxRatio, assistanceOnceLowestRatio, max, assistancePeopleNumber-i);
            ratios.add(ratio);
            assistanceMaxRatio -= ratio;
        }
        return ratios;
    }

}
