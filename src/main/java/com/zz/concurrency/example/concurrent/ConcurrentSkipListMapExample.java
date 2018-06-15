package com.zz.concurrency.example.concurrent;

import com.zz.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

/**
 * ConcurrentSkipListMap
 *
 * 按照key排序
 */
@Slf4j
@NotThreadSafe
public class ConcurrentSkipListMapExample {
    //请求总数
    public static int clientTotal = 5000;
    //线程总数
    public static int threadTotal = 200;

    //array list
    private static Map<Integer,Integer> map = new ConcurrentSkipListMap<>();

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);//信号量 允许并发的数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);//线程awaiting，计数器向下减减到0就继续执行线程,可以阻塞进程
        for(int i = 0;i < clientTotal; i++){
            final int temp = i;
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    put(temp);
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
        log.info("{}",map.size());
    }

    private static void put(int i){
        map.put(i,1);
    }
}
