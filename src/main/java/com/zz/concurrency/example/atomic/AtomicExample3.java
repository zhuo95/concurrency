package com.zz.concurrency.example.atomic;


import com.zz.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicIntegerFieldUpdater
 *
 *用原子性更新类里的某个字段
 */

@Slf4j
@ThreadSafe
public class AtomicExample3 {

    private static AtomicIntegerFieldUpdater<AtomicExample3> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample3.class,"count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {
        AtomicExample3 example3 = new AtomicExample3();

        if(updater.compareAndSet(example3,100,120)){
            log.info("update success 1:{}",example3.getCount());
        }

        if(updater.compareAndSet(example3,100,120)){
            log.info("update success 2:{}",example3.getCount());
        }else {
            log.error("update failed 2:{}",example3.getCount());
        }
    }
}

