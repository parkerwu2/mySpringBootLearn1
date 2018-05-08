package com.wjz.springboot1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jingzhi.wu on 2018/5/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate; // 处理字符串

    @Autowired
    private RedisTemplate redisTemplate; // 处理对象
    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("papa", "124");
        String val= stringRedisTemplate.opsForValue().get("papa");
        System.out.println(val);
        String val2= stringRedisTemplate.opsForValue().get("parker");
        System.out.println(val2);
    }
}
