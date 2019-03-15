package com.wjz.springboot1.seckill.method2;

import com.wjz.springboot1.util.JedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by jingzhi.wu on 2019/3/15.
 */
@Controller
@RequestMapping(value = "/seckill2")
@Slf4j
public class SecKillM2Controller {
    @Autowired
    private JedisCache jedisCache;

    private LinkedBlockingQueue goods;

    private final String rediskey = "rediskey";

    @PostConstruct
    public void init(){
        goods = new LinkedBlockingQueue();
        // 10个商品，标记为0-9号
        for (int i = 0; i < 10; i++){
            goods.offer(i);
        }
    }

    private String buyGood(){
        Object result;
        if ((result = goods.poll()) != null){
            return result.toString();
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/buy")
    @ResponseBody
    public String buy(@RequestBody String userName){
        log.info("user:"+userName + " begin to buy");
        if (tryLock(userName)){
            String goodNo = buyGood();
            if (goodNo != null){
                log.info("user:"+userName+" success buy good no:" +goodNo);
                return "1";
            } else {
                log.info("user:"+userName+" not enough good");
                return "0";
            }
        } else {
            log.info("user:"+userName+" fail to buy");
            return "2";
        }

    }

    private boolean tryLock(String userName){
        String currentStr = String.valueOf(System.currentTimeMillis());
        Long result = jedisCache.setnx(rediskey, currentStr);
        log.info("key:"+userName + ",result:"+result);
        if (result.equals(0l)){
            jedisCache.expire(userName, 5);
            return true;
        } else {
            return false;
        }
    }

}
