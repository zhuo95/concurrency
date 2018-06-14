package com.zz.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.zz.concurrency.annotations.ThreadSafe;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Collection.unmodifiableMap
 *
 */

@ThreadSafe
public class immutableExample2 {

    private static Map<Integer,Integer> map = Maps.newHashMap();

    static{
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        //会抛出异常,不能修改
        map.put(1,3);

    }

}
