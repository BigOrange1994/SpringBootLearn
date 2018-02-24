package cn.net.bigorange.helper.job;

import cn.net.bigorange.job.SimpleJob;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by bigorange on 2018/2/22.
 */
public class SimpleTriggerRunner {

    public static void main(String[] args) {
        // 旧的版本  setter风格
        // JobDetailImpl jobDetail = new JobDetailImpl("job1", "jgroup1", SimpleJob.class);

        // 新版本的DSL风格
        JobDetail jobDetail = newJob(SimpleJob.class)
                .withIdentity("job2", "jgroup2")
                .usingJobData("name", "quartz")
                .build();
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "tgroup1")
                .startAt(new Date())
                .withSchedule(
                        simpleSchedule()
                        .withIntervalInSeconds(2)
                        .withRepeatCount(100)
                )
                .build();
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
