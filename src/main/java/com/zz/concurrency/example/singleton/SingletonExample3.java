package com.zz.concurrency.example.singleton;

import com.zz.concurrency.annotations.NotRecommend;
import com.zz.concurrency.annotations.ThreadSafe;

/**
 * 懒汉模式
 * 在第一次使用的时候创建实例
 *
 * 通过synchronized关键词限制访问但是增加了开销，所以不推荐这么写
 */

@ThreadSafe
@NotRecommend
public class SingletonExample3 {
    //私有构造函数
    private SingletonExample3(){}

    //单例对象
    private static SingletonExample3 instance = null;

    //静态工厂方法 加了synchronize 关键词，让一次只有一个线程访问
    public static synchronized SingletonExample3 getInstance(){
        if(instance==null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
