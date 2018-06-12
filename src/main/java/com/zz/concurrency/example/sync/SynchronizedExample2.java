package com.zz.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized 关键词
 */

@Slf4j
public class SynchronizedExample2 {

    public static void test1(int j){
        //修饰类，作用对象是所有对象
        synchronized (SynchronizedExample2.class){
            for(int i=0;i<10;i++){
                log.info("test1 {} = {}" ,j,i);
            }
        }
    }

    //修饰一个静态方法,作用范围是这个方法，作用对象是所有对象 example1和example2
    public static synchronized void test2(int j){
        for(int i=0;i<10;i++){
            log.info("test2 {} = {}",j ,i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(()->{
            example1.test2(1);
        });

        executorService.execute(()->{
            example2.test2(2);
        });
    }
}
