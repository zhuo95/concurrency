package com.zz.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch
 *
 * 给任务指定时间
 */

@Slf4j
public class CountDownLatchExample2 {

    private static int threadCount = 200;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i=0;i<threadCount;i++){
            final int threadNum = i;
            exec.execute(()->{
                try{
                    test2(threadNum);
                }catch (Exception e){
                    log.error("exception",e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        //timeoout时间
        countDownLatch.await(10,TimeUnit.MICROSECONDS);
        log.info("finish");
        exec.shutdown();
    }

    private static void test2(int threadNum) throws Exception{
        Thread.sleep(100);
        log.info("{}",threadNum);
    }
}
