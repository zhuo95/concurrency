package com.zz.concurrency.example.singleton;

import com.zz.concurrency.annotations.NotRecommend;
import com.zz.concurrency.annotations.NotThreadSafe;
import com.zz.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式 -->双重同步锁单例模式
 * 在第一次使用的时候创建实例
 *
 *
 */

@NotThreadSafe
@NotRecommend
public class SingletonExample4 {
    //私有构造函数
    private SingletonExample4(){}

    //单例对象
    private static SingletonExample4 instance = null;

    /**
     * 执行如下操作的步骤
     * 1. memory = allocate() 分配内存空间
     * 2. 初始化对象
     * 3. instance = memory 设置instance指向刚分配的内存
     *
     * But 在多线程的时候cpu可能会指令重排:
     * 1. memory = allocate() 分配内存空间
     * 3. instance = memory 设置instance指向刚分配的内存
     * 2. 初始化对象
     *
     */

    //静态工厂方法
    public static SingletonExample4 getInstance(){
        if(instance==null){                                     //B线程执行到这一步，会发现instance已经有值了，就会直接return,但是A还在new没有初始化完成
            synchronized (SingletonExample4.class){ //同步锁
                if(instance==null) //双重检测机制
                instance = new SingletonExample4();             //A线程在这一步
            }

        }
        return instance;
    }
}
