package com.zz.concurrency.example.aqs;

/**
 * CyclicBarrier
 * 多个线程之间相互等待
 *
 * 如下设置成5，就是会等待5个线程完成后再继续await函数之后的代码
 *
 * 还支持设置等待时间 见example2
 *
 * 在new的时候还能指定一个函数，到达屏障的时候优先调用这个函数
 */


import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
public class CyclicBarrierExample1 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{ //在new的时候还能指定一个函数
        log.info("callback");
    });

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i=0;i<5;i++){
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(()->{
                try{
                    race(threadNum);
                }catch (Exception e){
                    log.error("exception",e);
                }
            });
        }
        executor.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready",threadNum);
        cyclicBarrier.await();
        log.info("{} continue",threadNum);
    }
}
