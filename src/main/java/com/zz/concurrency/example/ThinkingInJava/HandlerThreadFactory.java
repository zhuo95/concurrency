package com.zz.concurrency.example.ThinkingInJava;

import java.util.concurrent.ThreadFactory;

public class HandlerThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r){
        Thread t = new Thread(r); // create Thread
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        return t;
    }
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t.getId()+"catch"+e); //异常处理操作
    }
}
