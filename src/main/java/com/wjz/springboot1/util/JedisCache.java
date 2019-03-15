package com.wjz.springboot1.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * Created by jingzhi.wu on 2019/3/15.
 */
@Component
public class JedisCache {
    @Autowired
    private JedisPool jedisPool;

    public redis.clients.jedis.Jedis getResource() {
        //获取Jedis连接池资源
        return jedisPool.getResource();
    }

    public Long setnx(String key, String value) {
        redis.clients.jedis.Jedis jedis=null;
        jedis=getResource();
        Long result = jedis.setnx(key, value);
        return result;
    }

    /** key  value: seconds
     */
    public void expire(String key, int seconds){
        redis.clients.jedis.Jedis jedis=null;
        jedis=getResource();
        jedis.expire(key, seconds);
    }

    @SuppressWarnings("deprecation")
    public void returnResource(redis.clients.jedis.Jedis jedis) {
        if(jedis!=null) {
            //释放jedisPool资源
            jedisPool.returnResource(jedis);
        }
    }
    /**
     *实现数据写入的setInfo方法
     */
    public void setInfo(String key, Map<String, String> value) {
        redis.clients.jedis.Jedis jedis=null;
        try {
            //获取jedis实例连接
            jedis=getResource();
            //选择使用的数据库(0-15,如不指定默认为0）
            jedis.select(15);
            //使用hmset方法（key，value(hashmap<key,value>)）
            jedis.hmset(key, value);
        }catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }finally {
            returnResource(jedis);
        }
    }
    /**
     *实现数据查询的get方法
     */
    public Map<String, String> get(String key){
        redis.clients.jedis.Jedis jedis=null;
        Map<String, String> result=null;
        try {
            jedis=getResource();
            jedis.select(15);
            //通过key返回其所有的subkey及value
            jedis.hgetAll(key);
            result=jedis.hgetAll(key);
        }catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }finally {
            returnResource(jedis);
        }
        return result;
    }
}
