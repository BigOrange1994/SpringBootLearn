package cn.net.bigorange.job.spring;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by bigorange on 2018/2/26.
 */
public class MySpringJob implements Job{

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Map dataMap = context.getJobDetail().getJobDataMap();
        String size = (String) dataMap.get("size");
        ApplicationContext ctx = (ApplicationContext) dataMap.get("applicationContext");
        System.out.println("size = " + size);
        dataMap.put("size", size + "0");

    }

}
