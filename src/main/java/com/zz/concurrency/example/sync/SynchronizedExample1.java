package com.zz.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized 关键词
 */

@Slf4j
public class SynchronizedExample1 {

    //修饰代码块
    public void test1(int j){
        //修饰代码块，作用范围是大括号括起来的范围，作用对象是调用这个代码的对象
        synchronized (this){
            for(int i=0;i<10;i++){
                log.info("test1 {} = {}" ,j,i);
            }
        }
    }

    //修饰一个方法,作用范围是这个方法，作用对象是调用这个方法的对象
    //子类继承父类的方法是带不上synchronized关键字的
    public synchronized void test2(int j){
        for(int i=0;i<10;i++){
            log.info("test2 {} = {}",j ,i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
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
