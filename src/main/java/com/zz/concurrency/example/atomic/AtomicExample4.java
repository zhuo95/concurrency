package com.zz.concurrency.example.atomic;

import com.zz.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * AtomicBoolean
 * 如何让某一段代码只执行一次
 */

@Slf4j
@ThreadSafe
public class AtomicExample4 {

    public static AtomicBoolean isHappened = new AtomicBoolean();

    //请求总数
    public static int clientTotal = 5000;
    //线程总数
    public static int threadTotal = 200;


    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);//信号量 允许并发的数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);//线程awaiting，计数器向下减减到0就继续执行线程,可以阻塞进程
        for(int i = 0;i < clientTotal; i++){
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        //保证线程全执行完毕后才log count
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",isHappened.get());
    }

    private static void test(){
        if(isHappened.compareAndSet(false,true)) //只会执行一次,原子性
        log.info("execute");
    }

}
