package com.zz.concurrency.cache;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;


/**
 * Redis
 *
 *
 */

@Component
public class RedisClient {

    @Resource(name="redisPool")
    private JedisPool jedisPool;

    //set
    public void set(String key ,String value) throws Exception{
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.set(key,value);
        }finally {
            if(jedis!=null)
            jedis.close();
        }
    }

    //get
    public void get(String key) throws Exception{
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.get(key);
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }
}
