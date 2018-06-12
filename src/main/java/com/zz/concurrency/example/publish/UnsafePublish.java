package com.zz.concurrency.example.publish;

import com.zz.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 通过共有方法可以操作states field,可能有多个线程同时操作
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a","b","c"};

    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
      UnsafePublish unsafePublish = new UnsafePublish();
      log.info("{}", Arrays.toString(unsafePublish.getStates()));

      unsafePublish.getStates()[0] = "d";
      log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}
