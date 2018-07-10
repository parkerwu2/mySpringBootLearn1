package com.wjz.springboot1;

import com.wjz.springboot1.persistence.dao.OrderPoMapper;
import com.wjz.springboot1.persistence.model.OrderPo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;

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

    @Autowired
    private OrderPoMapper orderPoMapper;
    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("papa", "124");
        String val= stringRedisTemplate.opsForValue().get("papa");
        System.out.println(val);
        String val2= stringRedisTemplate.opsForValue().get("parker");
        System.out.println(val2);
    }

    @Test
    public void testInsert() throws Exception{
        OrderPo po = new OrderPo();
        Date now = new Date();
        po.setCreateTime(now);
        po.setUpdateTime(now);
        po.setTradeId(1);
        po.setUserId("testusr");
        po.setOrderNo("testorderno");
        po.setMerchantId("testmerchantid");
        po.setDeleteFlag(0);
        int c = orderPoMapper.insertSelective(po);
        System.out.println(c);
    }
}
