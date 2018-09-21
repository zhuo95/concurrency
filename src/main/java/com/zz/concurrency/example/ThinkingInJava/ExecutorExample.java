package com.zz.concurrency.example.ThinkingInJava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {

    public static void main(String[] args) {

//        for(int i=0;i<5;i++){
//            new Thread(new LiftOff()).start();
//            System.out.println("waiting for liftoff");
//        }

        ExecutorService exe = Executors.newCachedThreadPool();
        //使用Executor
        for(int i=0;i<5;i++){
            exe.execute(new LiftOff());
        }

        exe.shutdown();
    }

}

class LiftOff implements Runnable{
    protected int countDown = 10; //default

    private static int taskCount = 0;

    private final int id = taskCount++;

    public LiftOff(){}

    public LiftOff(int countDown){
        this.countDown = countDown;
    }

    public String status(){
        return "#" + id +"(" +(countDown>0? countDown : "LiftOff" ) + ").";
    }
    @Override
    public void run() {
        while(countDown-->0){
            System.out.println(status());
            Thread.yield();
        }
    }
}
