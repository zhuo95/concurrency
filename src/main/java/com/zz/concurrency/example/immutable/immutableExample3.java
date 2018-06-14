package com.zz.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.zz.concurrency.annotations.NotThreadSafe;
import com.zz.concurrency.annotations.ThreadSafe;

import java.util.Collections;
import java.util.Map;

/**
 * Immutable
 *
 */

@ThreadSafe
public class immutableExample3 {

    private final static ImmutableList list = ImmutableList.of(1,2,3);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer,Integer> map = ImmutableMap.of(1,2,3,4,5,6);
    private final static ImmutableMap<Integer,Integer> map2 = ImmutableMap.<Integer,Integer>builder().put(1,2).put(3,4).put(5,6).build();

    public static void main(String[] args) {
        list.add(4); //不行，会抛出异常
        set.add(4); //不行
        map.put(1,3); //不行
    }

}
