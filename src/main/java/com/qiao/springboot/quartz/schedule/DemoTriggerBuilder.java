package com.qiao.springboot.quartz.schedule;

import org.quartz.*;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import static com.qiao.springboot.quartz.schedule.DemoJobDetailBuilder.demoTriggerGroup;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule;

public class DemoTriggerBuilder {

    public static Trigger build(ScheduleData scheduleData, String name) {
        if (ScheduleType.ONE_TIME.equals(scheduleData.getScheduleType())) {
            Long startTime = scheduleData.getStartTime();
            Date date = new Date(startTime);
            Trigger runOnceTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(name, demoTriggerGroup)
                    .withDescription("Trigger will start at " + date)
                    .startAt(date)
                    .build();
            return runOnceTrigger;
        } else if (ScheduleType.SCHEDULE.equals(scheduleData.getScheduleType())) {
            String periodStart = scheduleData.getPeriodStart();
            String periodEnd = scheduleData.getPeriodEnd();

            int periodStartHour = 0;
            int periodStartMinute = 0;
            int periodStartSecond = 0;
            String[] periodStartArr = periodStart.split(":");
            if (periodStartArr.length == 3) {
                periodStartHour = Integer.parseInt(periodStartArr[0]);
                periodStartMinute = Integer.parseInt(periodStartArr[1]);
                periodStartSecond = Integer.parseInt(periodStartArr[2]);
            }

            int periodEndArrHour = 0;
            int periodEndArrMinute = 0;
            int periodEndArrSecond = 0;
            String[] periodEndArrArr = periodEnd.split(":");
            if (periodEndArrArr.length == 3) {
                periodEndArrHour = Integer.parseInt(periodEndArrArr[0]);
                periodEndArrMinute = Integer.parseInt(periodEndArrArr[1]);
                periodEndArrSecond = Integer.parseInt(periodEndArrArr[2]);
            }

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(name, demoTriggerGroup)
                    .withDescription("Trigger will start at " + scheduleData.getSchedule())
                    .withSchedule(dailyTimeIntervalSchedule()
                            .onDaysOfTheWeek(Arrays.stream(scheduleData.getSchedule().split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toSet()))
                            .startingDailyAt(TimeOfDay.hourMinuteAndSecondOfDay(periodStartHour, periodStartMinute, periodStartSecond))
                            .endingDailyAt(TimeOfDay.hourMinuteAndSecondOfDay(periodEndArrHour, periodEndArrMinute, periodEndArrSecond))
                            .withIntervalInMinutes(scheduleData.getInterval()))
                    .build();
            return trigger;
        } else if (ScheduleType.CRON.equals(scheduleData.getScheduleType())) {
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(name, demoTriggerGroup)
                    .withSchedule(cronSchedule(scheduleData.getCron()))
                    .build();
            return trigger;
        }

        return null;
    }

}
