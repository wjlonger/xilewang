package com.wuwei.consumer.quartz.controller;

import com.wuwei.base.quartz.model.XiLeWangQuartz;
import com.wuwei.base.quartz.service.XiLeWangQuartzService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@RestController
@RequestMapping("/job")
public class XiLeWangQuartzController {

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private XiLeWangQuartzService xiLeWangQuartzService;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @PostMapping("/add")
    public int save(XiLeWangQuartz xiLeWangQuartz){
        try {
            //如果是修改  展示旧的 任务
            if(xiLeWangQuartz.getOldJobGroup() != null){
                JobKey key = new JobKey(xiLeWangQuartz.getOldJobName(),xiLeWangQuartz.getOldJobGroup());
                scheduler.deleteJob(key);
            }
            Class cls = Class.forName(xiLeWangQuartz.getJobClassName()) ;
            cls.newInstance();
            //构建job信息
            JobDetail job = JobBuilder.newJob(cls).withIdentity(xiLeWangQuartz.getJobName(),
                    xiLeWangQuartz.getJobGroup())
                    .withDescription(xiLeWangQuartz.getDescription()).build();
            // 触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(xiLeWangQuartz.getCronExpression());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger"+xiLeWangQuartz.getJobName(), xiLeWangQuartz.getJobGroup())
                    .startNow().withSchedule(cronScheduleBuilder).build();
            //交由Scheduler安排触发
            scheduler.scheduleJob(job, trigger);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @PostMapping("/list")
    public List<XiLeWangQuartz> list(XiLeWangQuartz xiLeWangQuartz,Integer pageIndex,Integer pageSize){
        return xiLeWangQuartzService.listXiLeWangQuartz(xiLeWangQuartz, pageIndex, pageSize);
    }

    @PostMapping("/trigger")
    public  int trigger(XiLeWangQuartz xiLeWangQuartz) {
        try {
            JobKey key = new JobKey(xiLeWangQuartz.getJobName(),xiLeWangQuartz.getJobGroup());
            scheduler.triggerJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @PostMapping("/pause")
    public int pause(XiLeWangQuartz xiLeWangQuartz) {
        try {
            JobKey key = new JobKey(xiLeWangQuartz.getJobName(),xiLeWangQuartz.getJobGroup());
            scheduler.pauseJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @PostMapping("/resume")
    public  int resume(XiLeWangQuartz xiLeWangQuartz) {
        try {
            JobKey key = new JobKey(xiLeWangQuartz.getJobName(),xiLeWangQuartz.getJobGroup());
            scheduler.resumeJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @PostMapping("/remove")
    public int remove(XiLeWangQuartz xiLeWangQuartz) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(xiLeWangQuartz.getJobName(), xiLeWangQuartz.getJobGroup());
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(xiLeWangQuartz.getJobName(), xiLeWangQuartz.getJobGroup()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

}
