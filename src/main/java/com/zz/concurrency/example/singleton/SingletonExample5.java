package com.zz.concurrency.example.singleton;

import com.zz.concurrency.annotations.NotRecommend;
import com.zz.concurrency.annotations.NotThreadSafe;
import com.zz.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式 -->双重同步锁单例模式
 *
 * 解决example4问题
 */

@ThreadSafe
public class SingletonExample5 {
    //私有构造函数
    private SingletonExample5(){}

    //单例对象 用volatile关键字限制重拍
    private volatile static SingletonExample5 instance = null;

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
     * 用volatile关键词可以限制重排
     */

    //静态工厂方法
    public static SingletonExample5 getInstance(){
        if(instance==null){
            synchronized (SingletonExample5.class){ //同步锁
                if(instance==null) //双重检测机制
                instance = new SingletonExample5();
            }

        }
        return instance;
    }
}
