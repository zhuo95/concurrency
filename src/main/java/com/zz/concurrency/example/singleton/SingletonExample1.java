package com.zz.concurrency.example.singleton;

import com.zz.concurrency.annotations.NotThreadSafe;

/**
 * 懒汉模式
 * 在第一次使用的时候创建实例
 */

@NotThreadSafe
public class SingletonExample1 {
    //私有构造函数
    private SingletonExample1(){}

    //单例对象
    private static SingletonExample1 instance = null;

    //静态工厂方法
    public static SingletonExample1 getInstance(){
        if(instance==null){
            instance = new SingletonExample1();
        }
        return instance;
    }

}
