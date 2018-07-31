package com.wjz.springboot1.seckill;

import com.wjz.springboot1.util.RestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * Created by jingzhi.wu on 2018/7/30.
 */
@Service
@Slf4j
public class SecKillService {
    @Autowired
    private StringRedisTemplate redisTemplate; // 处理对象
    private String watchkeys = "watchkeys";// 监视keys

    public SecKillResponse buy(SecKillRequest request) throws Exception {
        log.info("seckillservice start:" + RestUtil.objectToJson(request));
        SecKillResponse response = new SecKillResponse();
        //先从缓存里获取库存，如果有库存，则发送消息队列通知数据库更新DB库存，下单记录消息
        try {
//            redisTemplate.watch(watchkeys);// watchkeys
            Object val = redisTemplate.opsForValue().get(watchkeys);
            int valint = Integer.valueOf(String.valueOf(val));
            if (valint <= 100 && valint>=1) {
                SessionCallback sessionCallback = new SessionCallback() {
                @Override
                public Object execute(RedisOperations redisOperations) throws DataAccessException {
                    redisOperations.watch(watchkeys);
                    redisOperations.multi();
                    redisOperations.opsForValue().increment(watchkeys, -1);
                    return redisOperations.exec();
                    }
                };
                List<Object> list = (List<Object>)redisTemplate.execute(sessionCallback);
                if (CollectionUtils.isEmpty(list)){
                    String failinfo1="用户：" + request.getRequester() + "商品被抢购完毕，抢购失败";
//                    log.info(failinfo1);
                    response.setCode("-1");
                    response.setMessage(failinfo1);
                } else {
                    for(Object succ : list){
                        String succinfo="用户：" + request.getRequester() + "抢购成功，当前抢购成功人数:"
                                + (1-(valint-100));
                        log.info(succinfo);
                         /* 抢购成功业务逻辑 */
                        log.info("start to send queue:" + succinfo);
                        response.setMessage(succinfo);
                    }
                }
            } else {
                String failinfo1="用户：" + request.getRequester() + "商品被抢购完毕，抢购失败";
//                log.info(failinfo1);
                response.setCode("-1");
                response.setMessage(failinfo1);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            Object val = redisTemplate.opsForValue().get(watchkeys);
//            log.info("err now val=" + val);
            response.setCode("-1");
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
