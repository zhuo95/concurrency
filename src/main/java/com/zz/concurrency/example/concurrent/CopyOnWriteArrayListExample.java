package com.zz.concurrency.example.concurrent;

import com.zz.concurrency.annotations.NotThreadSafe;
import com.zz.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * CopyOnWriteArrayList
 *
 */
@Slf4j
@ThreadSafe
public class CopyOnWriteArrayListExample {
    //请求总数
    public static int clientTotal = 5000;
    //线程总数
    public static int threadTotal = 200;

    //copyOnWriteArrayList list
    private static List<Integer> lis = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);//信号量 允许并发的数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);//线程awaiting，计数器向下减减到0就继续执行线程,可以阻塞进程
        for(int i = 0;i < clientTotal; i++){
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    add();
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
        log.info("{}",lis.size());
    }

    private static void add(){
        lis.add(1);
    }
}
