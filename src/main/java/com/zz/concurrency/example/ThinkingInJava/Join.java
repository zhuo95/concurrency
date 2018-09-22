package com.zz.concurrency.example.ThinkingInJava;

/**
 * 在zy中调用了zz.join(),所以zy会被挂起，知道zz执行完成
 */

public class Join {

    public static void main(String[] args) {

        Sleeper zz = new Sleeper("zz",1000);

        Joiner zy  = new Joiner("zy",zz);
    }

}

class Sleeper extends Thread{
    private int duration;
    private String name;

    public Sleeper(String name,int duration){
        this.duration = duration;
        this.name = name;
        start();
    }

    public void run(){
        try{
            sleep(duration);
        }catch (InterruptedException e){
            System.out.println(this.name + " was interrupted");
            return;
        }
        System.out.println(this.name+" has awakened");
    }
}

class Joiner extends Thread{
    private Sleeper sleeper;
    private String name;

    public Joiner(String name,Sleeper sleeper){
        this.name = name;
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try{
            sleeper.join();
        }catch (InterruptedException e){
            System.out.println("Interrupted");
        }
        System.out.println(this.name+" join completed");
    }
}
