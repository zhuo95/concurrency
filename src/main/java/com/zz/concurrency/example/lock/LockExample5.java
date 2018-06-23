package com.zz.concurrency.example.lock;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * reentrantLock condition
 *
 * 分类唤醒
 */
@Slf4j
public class LockExample5 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock();  //线程加入aqs等待队列
                log.info("wait signal"); // 1
                condition.await(); //使用await之后,从aqs队列移除，加入condition队列
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal"); // 4
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock(); //因为线程一释放锁，被唤醒，得到锁
            log.info("get lock"); // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();  //发送信号 condition中的线程加入aqs队列
            log.info("send signal ~ "); // 3
            reentrantLock.unlock();
        }).start();
    }
}



