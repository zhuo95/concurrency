package com.zz.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future
 *
 * 能够接受线程执行完了之后返回的值
 */

@Slf4j
public class FutureExample {

    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            log.info("do callable");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exe = Executors.newCachedThreadPool();
        Future<String> future = exe.submit(new MyCallable());
        log.info("do other things");
        Thread.sleep(1000);
        String res = future.get();
        log.info("result:{}",res);
    }

}
