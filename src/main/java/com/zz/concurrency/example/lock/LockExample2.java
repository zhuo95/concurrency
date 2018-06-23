package com.zz.concurrency.example.lock;

import com.zz.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@ThreadSafe

/**
 * ReentrantReadWriteLock
 *
 * 分读和写的锁
 * 实现的是悲观读取，写入的时候不允许有读锁
 * 读取多的时候，写少的时候容易出现饥饿
 * 就是写一直在等待
 */
public class LockExample2 {
    private final Map<String, Data> map = new TreeMap<>();

    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public Data get(String key){
        readLock.lock();
        try{
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys(){
        readLock.lock();
        try{
            return map.keySet();
        }finally {
            readLock.unlock();
        }
    }

    public void put(String key, Data data){
        writeLock.lock();
        try{
            map.put(key,data);
        }finally {
            writeLock.unlock();
        }
    }

    class Data{

    }

}
