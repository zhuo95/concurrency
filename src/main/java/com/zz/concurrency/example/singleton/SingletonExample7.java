package com.zz.concurrency.example.singleton;

import com.zz.concurrency.annotations.NotThreadSafe;
import com.zz.concurrency.annotations.Recommend;
import com.zz.concurrency.annotations.ThreadSafe;

/**
 * 通过枚举,最安全
 *
 */

@ThreadSafe
@Recommend
public class SingletonExample7 {
    //私有构造函数
    private SingletonExample7(){}

    private static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getSingleton();
    }

    //enum
    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        //jvm保证这个方法只会被调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getSingleton(){
            return singleton;
        }
    }
    

}
