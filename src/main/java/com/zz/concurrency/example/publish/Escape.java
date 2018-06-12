package com.zz.concurrency.example.publish;

import com.zz.concurrency.annotations.NotRecommend;
import com.zz.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 对象溢出
 *
 * 在Escape构造的时候启动一个新线程，会造成this引用的溢出，新线程在构造完成前就看到this
 *
 * 不能在构造函数中启动新线程
 */

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {
    private int thisCanBeEscape = 1;

    public Escape(){
        new InnerClass();
    }

    private class InnerClass{

        public InnerClass(){
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        Escape e = new Escape();

    }
}
