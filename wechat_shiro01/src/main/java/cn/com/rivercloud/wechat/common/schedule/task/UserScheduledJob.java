package cn.com.rivercloud.wechat.common.schedule.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserScheduledJob implements Job {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        System.out.println("111111111111111111111111111111111CRON ----> schedule job1 is running ... + " + name + "  ---->  " + dateFormat.format(new Date()));
    }
}
