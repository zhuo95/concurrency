package com.zz.concurrency.example.atomic;

import com.zz.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * LongAdder example 与 atomicLong 对比
 *
 * CAS在竞争激烈的时候 会多次循环操作 浪费资源
 *
 * 对于Long和Double类型 JVM 会64位读写操作拆成32位操作
 *
 * LongAdder 会把热点数据分离，比如会把Long的value分成base加上cell数组，这个value实际值由多个cell的值加上base的值，这样就把单点的并发压力
 * 分散到多个节点
 *
 * 在高并发的时候longAdder比较好
 */

@Slf4j
@ThreadSafe
public class LongAddExample {

    //请求总数
    public static int clientTotal = 5000;
    //线程总数
    public static int threadTotal = 200;

    public static LongAdder count = new LongAdder();

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
        log.info("count:{}",count);
    }

    private static void add(){
        count.increment();//++
    }
}
