package com.wjz.springboot1;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

/**
 * Created by jingzhi.wu on 2018/8/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestRedisLua {
    @Autowired
    DefaultRedisScript<Boolean> redisScript;
    @Autowired
    private StringRedisTemplate redisTemplate; // 处理字符串

    @Test
    public void testlua(){
        String key = "testredislua";

        redisTemplate.delete(key);
        redisTemplate.opsForValue().set(key, "hahaha");
        String s = redisTemplate.opsForValue().get(key);
        log.info(s);

        redisTemplate.execute(redisScript, Collections.singletonList(key), "hahaha", "3333");
        s = redisTemplate.opsForValue().get(key);
        log.info(s);

    }
}
