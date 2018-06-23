package com.zz.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * CountDownLatch
 *
 * 设置一个线程数，一个线程执行完后countdown
 * await会保证countdown到0之后才执行之后的代码
 *
 * 核心是countdown函数和await函数
 */

@Slf4j
public class SemaphoreExample1 {

    private static int threadCount = 200;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(20);

        for(int i=0;i<threadCount;i++){
            final int thread = i;
            exec.execute(()->{
                try{
                    semaphore.acquire(3); //获取许可 也可以允许多个
                    test(thread);
                    semaphore.release(3); //释放许可 也可以释放多个
                }catch (Exception e){
                    log.error("exception",e);
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws Exception{
        log.info("{}",threadNum);
        Thread.sleep(1000);
    }
}
