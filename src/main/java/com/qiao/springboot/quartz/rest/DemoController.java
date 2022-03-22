package com.qiao.springboot.quartz.rest;

import com.qiao.springboot.quartz.schedule.ScheduleData;
import com.qiao.springboot.quartz.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @PostMapping("start-job")
    public void startJob(@RequestParam String name, @RequestBody ScheduleData scheduleData) {
        demoService.startJob(name, scheduleData);
    }

    @PostMapping("stop-job")
    public void stopJob(@RequestParam String name) {
        demoService.stopJob(name);
    }

}
