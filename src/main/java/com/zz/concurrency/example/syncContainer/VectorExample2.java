package com.zz.concurrency.example.syncContainer;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * forEach,iterator,for循环做remove
 *
 * forEach原理是利用iterator，集合里面有一个field叫modCount，每更改一次如add就会加1，iterator里面有expectModCount，如果这两个值不一致就抛出异常
 *
 * 推荐使用for循环
 *
 * forEach和iterator如果要remove的话需要先遍历，再删除
 */
@Slf4j
public class VectorExample2 {

    //java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> v1){ // for each
        for(Integer i : v1 ){
            if(i.equals(3)) v1.remove(i);
        }
    }

    //java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> v2){ //iterator
        Iterator<Integer> iterator = v2.iterator();
        while(iterator.hasNext()){
            Integer i = iterator.next();
            if(i.equals(3)){
                v2.remove(i);
            }
        }
    }

    //成功
    private static void test3(Vector<Integer> v3) { // for
        for(int i=0;i<v3.size();i++){
            if(v3.get(i).equals(3)){
                v3.remove(i);
            }
        }
    }

        public static void main(String[] args) throws Exception{
       Vector<Integer> vector = new Vector<>();

       vector.add(1);
       vector.add(2);
       vector.add(3);

       test1(vector);
    }


}
