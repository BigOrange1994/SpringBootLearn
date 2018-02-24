package cn.net.bigorange.helper.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.List;
import java.util.Set;

/**
 * Created by bigorange on 2018/2/24.
 */
public class JdbcStoreJobRunner {

    public static void main(String[] args) {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            List<String> triggerGroups = scheduler.getTriggerGroupNames();
            for(String triggerGroupName : triggerGroups){
                Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(triggerGroupName));
                for (JobKey jobKey : jobKeys) {
                    String jobName = jobKey.getName();
                    String jobGroup = jobKey.getGroup();
                    Trigger trigger = (Trigger) scheduler.getTriggersOfJob(jobKey);
                    if(trigger instanceof SimpleTrigger && jobName.equals("trigger_cron")
                            &&jobGroup.equals("tgroup")){
                        scheduler.resumeJob(jobKey);
                    }

                }
                System.out.println("aa");
            }
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

}
