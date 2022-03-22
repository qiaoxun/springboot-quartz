package com.qiao.springboot.quartz.service;

import com.qiao.springboot.quartz.schedule.DemoJobDetailBuilder;
import com.qiao.springboot.quartz.schedule.DemoTriggerBuilder;
import com.qiao.springboot.quartz.schedule.ScheduleData;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DemoService {

    @Autowired
    private Scheduler scheduler;

    public void startJob(String name, ScheduleData scheduleData) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", name + " begin to work");
        JobDetail jobDetail = DemoJobDetailBuilder.build(name, map);
        Trigger trigger = DemoTriggerBuilder.build(scheduleData, name);

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void stopJob(String name) {
        try {
            scheduler.unscheduleJob(new TriggerKey(name, DemoJobDetailBuilder.demoTriggerGroup));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
