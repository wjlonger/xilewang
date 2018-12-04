package com.wuwei.test;

import java.util.ArrayList;
import java.util.List;

public class RedPacket {

    /**
     * 1.总金额不超过200*100  单位是分
     * 2.每个红包都要有钱，最低不能低于1分，最大金额不能超过200*100
     */
    private static final int MINMONEY = 8;
    private static final int MAXMONEY = 40;

    /**
     * 这里为了避免某一个红包占用大量资金，我们需要设定非最后一个红包的最大金额，
     * 我们把他设置为红包金额平均值的N倍
     */
    private static final double TIMES = 1.7;

    /**
     * 红包合法性校验
     * @param money
     * @param count
     * @return
     */
    private boolean isRight(int money,int count){
        double avg = money/count;
        //小于最小金额
        if (avg<MINMONEY) {
            return false;
        }else if (avg>MAXMONEY) {
            return false;
        }
        return true;
    }

    /**
     * 随机分配一个红包
     * @param money
     * @param minS：最小金额
     * @param maxS：最大金额
     * @param count
     * @return
     */
    private int randomRedPacket(int money,int minS,int maxS,int count){
        //若只有一个，直接返回红包
        if (count == 1) {
            return money;
        }
        //如果最大金额和最小金额相等，直接返回金额
        if (minS == maxS) {
            return minS;
        }
        int max = Math.min(maxS,money);
        //分配红包正确情况，允许红包的最大值
        int maxY = money - (count-1) * minS;
        //分配红包正确情况，允许红包最小值
        int minY = money-(count-1)*maxS;
        //随机产生红包的最小值
        int min = Math.max(minS,minY);
        //随机产生红包的最大值
        max = Math.min(max,maxY);
        //随机产生一个红包
        return (int)Math.rint(Math.random()*(max-min) +min);
    }

    /**
     * 拆分红包
     * @param money	红包金额
     * @param count 个数
     * @return
     */
    public List<Integer> splitRedPacket(int money, int count){
        //红包合法性分析
        if (!isRight(money, count)) {
            return null;
        }
        //红包列表
        List<Integer> list = new ArrayList<>();
        //每个红包的最大的金额为平均金额的TIMES倍
        int max = (int)(money * TIMES / count);
        max = Math.min(max,MAXMONEY);
        //分配红包
        for(int i=0;i<count;i++){
            int one = randomRedPacket(money, MINMONEY, max, count-i);
            list.add(one);
            money-=one;
        }
        return list;
    }

    public static void main(String[] args) {
        RedPacket redPacket = new RedPacket();
        System.out.println(redPacket.splitRedPacket(40, 3));
    }

}
