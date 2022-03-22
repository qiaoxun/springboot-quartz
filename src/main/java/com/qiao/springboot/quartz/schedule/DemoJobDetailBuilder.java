package com.qiao.springboot.quartz.schedule;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;

import java.util.Map;

public class DemoJobDetailBuilder {
    public static final String demoTriggerGroup = "demoTriggerGroup";

    public static JobDetail build(String name, Map<String, Object> map) {
        JobDetail jobDetail = JobBuilder.newJob().ofType(DemoJob.class)
                .storeDurably()
                .setJobData(new JobDataMap(map))
                .withIdentity(name, demoTriggerGroup)
                .withDescription("Invoke Demo Job service...")
                .build();

        return jobDetail;
    }
}
