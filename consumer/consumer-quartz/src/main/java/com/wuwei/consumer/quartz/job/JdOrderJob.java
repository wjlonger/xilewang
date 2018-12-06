package com.wuwei.consumer.quartz.job;


import com.wuwei.consumer.quartz.service.JdOrderService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class JdOrderJob implements Job, Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private JdOrderService jdOrderService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("运行开始");
        System.out.println("运行结束");
    }
}
