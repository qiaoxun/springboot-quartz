package com.qiao.springboot.quartz.schedule;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoJob implements Job {

    Logger logger = LoggerFactory.getLogger(DemoJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("Starting the job, at " + System.currentTimeMillis());
        JobDataMap data = context.getJobDetail().getJobDataMap();
        String message = data.getString("message");
        System.out.println(message);
        logger.info("Job finished, at " + System.currentTimeMillis());
    }
}
