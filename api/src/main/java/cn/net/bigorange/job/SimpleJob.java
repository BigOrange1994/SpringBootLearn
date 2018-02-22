package cn.net.bigorange.job;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.util.Date;

/**
 * Created by bigorange on 2018/2/22.
 */
public class SimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail detail = jobExecutionContext.getJobDetail();
        String name = detail.getJobDataMap().getString("name");
        System.out.println(name + " trigger time is: " + new Date());
    }
}
