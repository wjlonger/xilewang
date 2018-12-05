package com.wuwei.base.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wjl
 * @createtime 2018年11月21日11:07:34
 */
public final class IdGenerator {

    /**
     * 序列
     */
    private long sequence;
    /**
     * 毫秒内自增位数
     */
    private static final long sequenceBits = 12L;
    /**
     * sequence掩码，确保sequnce不会超出上限
     */
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);
    /**
     * 服务器ID
     */
    private long workerId;
    /**
     * 机器标识位数
     */
    private static final long workerIdBits = 5L;
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    /**
     * 机器ID偏左移12位
     */
    private static final long workerIdShift = sequenceBits;
    /**
     * 进程编码
     */
    private long datacenterId;
    /**
     * 数据中心标识位数
     */
    private static final long datacenterIdBits = 5L;
    private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    /**
     * 数据中心ID左移17位
     */
    private static final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 上次时间戳
     */
    private long lastTimestamp = -1L;
    /**
     * 时间毫秒左移22位
     */
    private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    /**
     * 计算标记时间
     */
    private static final long twepoch = 1288834974657L;
    private static IdGenerator idGenerator;

    private IdGenerator() {
        // 获取机器编码
        this.workerId = this.getMachineNum();
        // 获取进程编码
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        this.datacenterId = Long.valueOf(runtimeMXBean.getName().split("@")[0]).longValue();
        // 避免编码超出最大值
        this.workerId = workerId & maxWorkerId;
        this.datacenterId = datacenterId & maxDatacenterId;
    }

    static {
        idGenerator = new IdGenerator();
    }

    public static synchronized long nextId(){
        return idGenerator.getNextId();
    }

    private long getMachineNum(){
        long machinePiece;
        StringBuilder sb = new StringBuilder();
        Enumeration<NetworkInterface> e = null;
        try {
            e = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e1) {
            return "".hashCode();
        }
        while (e.hasMoreElements()) {
            NetworkInterface ni = e.nextElement();
            sb.append(ni.toString());
        }
        machinePiece = sb.toString().hashCode();
        return machinePiece;
    }

    public synchronized long getNextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            timestamp = lastTimestamp;
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

}