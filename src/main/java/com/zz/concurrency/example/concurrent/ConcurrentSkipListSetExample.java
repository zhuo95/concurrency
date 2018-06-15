package com.zz.concurrency.example.concurrent;

import com.zz.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.*;

/**
 * CopyOnWriteArraySet
 *
 * 在用set.removeAll()的时候不能保证安全，因为单个remove是安全的，
 * removeAll是多次调用remove（），在两次调用之间不能保证原子性
 */
@Slf4j
@ThreadSafe
public class ConcurrentSkipListSetExample {
    //请求总数
    public static int clientTotal = 5000;
    //线程总数
    public static int threadTotal = 200;

    //copyOnWriteArrayList list
    private static Set<Integer> set = new ConcurrentSkipListSet<Integer>();

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);//信号量 允许并发的数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);//线程awaiting，计数器向下减减到0就继续执行线程,可以阻塞进程
        for(int i = 0;i < clientTotal; i++){
            final int temp = i;
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    add(temp);
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
        log.info("{}",set.size());
    }

    private static void add(int i){
        set.add(i);
    }
}
