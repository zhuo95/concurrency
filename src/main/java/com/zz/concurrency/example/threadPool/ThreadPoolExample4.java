package com.zz.concurrency.example.threadPool;


import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPool
 *
 */
@Slf4j
public class ThreadPoolExample4 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5) ;

        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("schedule run");
            }
        },3,TimeUnit.SECONDS);

        //延迟一秒后，间隔三秒执行一次
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("schedule rate");
            }
        },1,3,TimeUnit.SECONDS);

        executorService.shutdown();

        //间隔5秒执行一次，相当于定时器
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("timer");
            }
        },new Date(),5*1000);
    }



}
