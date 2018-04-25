package com.wjz.springboot1.job;

/**
 * Created by jingzhi.wu on 2018/4/25.
 */

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class TestTask {
    @Scheduled(cron="0/1 * * * * ?")
    public void task01() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "...task01 " + new Date());
        TimeUnit.SECONDS.sleep(2);
    }

    @Scheduled(cron="0/1 * * * * ?")
    public void task02() {
        System.out.println(Thread.currentThread().getName() + "...task02 " + new Date());
    }

    @Scheduled(cron="0/1 * * * * ?")
    public void task03() {
        System.out.println(Thread.currentThread().getName() + "...task03 " + new Date());
    }
}
