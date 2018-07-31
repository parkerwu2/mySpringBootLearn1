package com.wjz.springboot1.seckill;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jingzhi.wu on 2018/7/30.
 */
@RestController
@RequestMapping(value = "/seckill")
@Slf4j
public class SecKillController {
    @Autowired
    private SecKillService secKillService;

    @RequestMapping(value = "/buy")
    public SecKillResponse buy(@RequestParam SecKillRequest request) throws Exception {
        return secKillService.buy(request);
    }
}
