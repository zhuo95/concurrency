package com.zz.concurrency.example.syncContainer;

import com.zz.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;


/**
 * 会发生数组越界，
 * 因为get方法虽然是synchronized 修饰，
 * 但是i 可能已经被remove了
 */

@Slf4j
@NotThreadSafe
public class VectorExampleNotSafe {

    //同步的array list
    private static Vector<Integer> lis = new Vector();

    public static void main(String[] args) throws Exception{

        while(true) {

            for (int i = 0; i < 10; i++) {
                lis.add(i);
            }
            Thread thread1 = new Thread() {
                public void run() {
                    for (int i = 0; i < lis.size(); i++) {
                        lis.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread() {
                public void run() {
                    for (int i = 0; i < lis.size(); i++) {
                        lis.get(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }


}
