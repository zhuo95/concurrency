package com.zz.concurrency.example.ThinkingInJava;


/**
 * synchronized修饰方法的时候锁住的对象研究：
 * synchronized方法锁的是对象。
 * 不同的对象用一个synchronized方法不会阻塞，
 * 相同的对象内如果有线程在用synchronized方法，整个对象会被锁住。
 */
public class SynchronizedObject {
    String name;

    public SynchronizedObject(String name) {
        this.name = name;
    }

    public synchronized void test(){
        System.out.println(name + " test");
        try{
            Thread.sleep(10000);
        }catch (InterruptedException e){
            System.out.println(e);
        }
        System.out.println("test执行完毕");
    }

    public synchronized void test2(){
        System.out.println(name + " test2");
    }

    public static void main(String[] args) {
        SynchronizedObject s = new SynchronizedObject("zz");
        thread t1 = new thread(s);
        thread2 t2 = new thread2(s);
        new Thread(t1).start();
        new Thread(t2).start();
    }
}

class thread implements Runnable {
    SynchronizedObject s;

    public thread(SynchronizedObject s) {
        this.s = s;
    }

    @Override
    public void run() {
        s.test();
    }
}

class thread2 implements Runnable {
    SynchronizedObject s;

    public thread2(SynchronizedObject s) {
        this.s = s;
    }

    @Override
    public void run() {
        s.test2();
    }
}
