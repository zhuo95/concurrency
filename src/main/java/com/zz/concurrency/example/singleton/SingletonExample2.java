package com.zz.concurrency.example.singleton;

import com.zz.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式
 *
 * 实例在装载的时候创建
 *
 * 问题：如果构造函数中存在太多的处理会导致类的加载很慢
 */

@ThreadSafe
public class SingletonExample2 {
    //私有构造函数
    private SingletonExample2(){}

    //单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    //静态工厂方法
    public static SingletonExample2 getInstance(){
        return instance;
    }
}
