package com.zz.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * FutureTask
 *
 * 和之前用future和callable类似
 */

@Slf4j
public class FutureTaskExample {
    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(5000);
                return "Done";
            }
        });

        new Thread(futureTask).start();
        log.info("do something else");
        Thread.sleep(1000);
        String res = futureTask.get();
        log.info("result:{}",res);
    }
}
