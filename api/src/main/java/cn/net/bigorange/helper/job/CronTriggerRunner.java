package cn.net.bigorange.helper.job;

import cn.net.bigorange.job.SimpleJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;
import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by bigorange on 2018/2/22.
 */
public class CronTriggerRunner {

    public static void main(String[] args) {
        // 旧的版本  setter风格
        // JobDetailImpl jobDetail = new JobDetailImpl("job1", "jgroup1", SimpleJob.class);

        // 新版本的DSL风格
        JobDetail jobDetail = newJob(SimpleJob.class)
                .withIdentity("job1", "jgroup1")
                .usingJobData("name", "cron quartz")
                .build();
        CronTrigger cronTrigger = (CronTrigger)newTrigger()
                .withIdentity("trigger_cron", "tgroup")
                .withSchedule(cronSchedule("0/5 * * * * ?"))
                .build();
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.scheduleJob(jobDetail, cronTrigger);
            scheduler.start();
            try {
                // 此处需要合适的时间，让任务线程去处理未完成的任务，防止主线程的立即停止而导致当前正在执行转态的任务线程停止
                Thread.currentThread().sleep(1000);
                System.out.println("当前线程：" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
