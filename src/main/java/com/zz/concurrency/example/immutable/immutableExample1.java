package com.zz.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.zz.concurrency.annotations.NotThreadSafe;

import java.util.Map;

/**
 * final关键词
 *
 * 基础数据类型不能修改值
 *
 * 引用类型不能修改指向
 */

@NotThreadSafe
public class immutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer,Integer> map = Maps.newHashMap();

    static{
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);
    }

    public static void main(String[] args) {
        //引用类型不能修改引用对象，但是能修改对象
        map.put(1,3);
        //a=2; 不行
    }

    //final还可以修饰传入的参数，传入后不能修改
    private void test(final int a){
        //a = 1; 不允许
    }
}
