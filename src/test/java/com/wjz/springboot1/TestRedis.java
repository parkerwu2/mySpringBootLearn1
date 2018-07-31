package com.wjz.springboot1;

import com.wjz.springboot1.persistence.dao.OrderPoMapper;
import com.wjz.springboot1.persistence.model.OrderPo;
import com.wjz.springboot1.seckill.SecKillRequest;
import com.wjz.springboot1.seckill.SecKillResponse;
import com.wjz.springboot1.seckill.SecKillService;
import com.wjz.springboot1.util.RestUtil;
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
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private SecKillService secKillService;

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
    public void test2() throws Exception{
        stringRedisTemplate.opsForValue().set("ax", "100");
        Long v = stringRedisTemplate.opsForValue().increment("ax", 1);
        System.out.println(v);
        Object val= stringRedisTemplate.opsForValue().get("ax");
        System.out.println(val);
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

    private String getName(int length){
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    @Test
    public void testSecKill() throws  Exception{
        System.out.println("seckill start");
        //设置库存
        stringRedisTemplate.opsForValue().set("watchkeys", "100");
        ExecutorService executor = Executors.newFixedThreadPool(32);  //20个线程池并发数
        int people = 20000;
        final CountDownLatch cdl = new CountDownLatch(people);
        for (int i = 0; i < people; i++) {//设置1000个人来发起抢购
            executor.execute(new Runnable() {
                @Override
                public void run() {
//                    System.out.println("inner thread start");
                    SecKillRequest request = new SecKillRequest();
                    request.setRequester("reqNum:" + getName(6));
                    try {
                        SecKillResponse response = secKillService.buy(request);
                        System.out.println(RestUtil.objectToJson(response));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
//                        System.out.println("inner thread end");
                        cdl.countDown();
                    }

                }
            });
        }
        cdl.await();
        executor.shutdown();
        System.out.println("seckill end");
    }
}
