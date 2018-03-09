package cn.net.bigorange.helper.job;

import cn.net.bigorange.job.SimpleJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.quartz.DateBuilder.dateOf;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by bigorange on 2018/2/26.
 */
public class CalendarTriggerRunner {

    public static void main(String[] args) {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            AnnualCalendar holidays = new AnnualCalendar();
            Calendar laborDay = new GregorianCalendar();
            laborDay.add(Calendar.MONTH, 5);
            laborDay.add(Calendar.DATE, 1);

            ArrayList<Calendar> calendars = new ArrayList<Calendar>();
            calendars.add(laborDay);

            holidays.setDaysExcluded(calendars);
            scheduler.addCalendar("holidays", holidays, false, false);

            Date runDate = dateOf(0, 0, 10, 1, 4);
            JobDetail jobDetail = newJob(SimpleJob.class)
                    .withIdentity("job2", "jgroup2")
                    .usingJobData("name", "quartz")
                    .build();
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "tgroup1")
                    .startAt(runDate)
                    .withSchedule(
                            simpleSchedule()
                                    .withIntervalInHours(1)
                                    .repeatForever()
                    )
                    .modifiedByCalendar("holidays")
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
