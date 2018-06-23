package com.zz.concurrency.example.aqs;

/**
 * CyclicBarrier
 * 多个线程之间相互等待
 *
 * 还支持设置等待时间 但会抛出异常需要捕捉
 *
 */


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CyclicBarrierExample2 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i=0;i<5;i++){
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(()->{
                try{
                    race2(threadNum);
                }catch (Exception e){
                    log.error("exception",e);
                }
            });
        }
        executor.shutdown();
    }

    private static void race2(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready",threadNum);
        try{
            cyclicBarrier.await(2000,TimeUnit.MILLISECONDS);
        }catch (Exception e){
            log.error("exception",e);
        }
        log.info("{} continue",threadNum);
    }
}
