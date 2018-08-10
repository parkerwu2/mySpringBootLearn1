package com.wjz.springboot1.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

/**
 * Created by jingzhi.wu on 2018/8/10.
 */
@Service
public class RedisLua {
    // 不包含lua结尾
    @Bean
    public DefaultRedisScript<Boolean> redisScript() {
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
        StringBuffer sb = new StringBuffer("lua/");
//        sb.append(name).append(".lua");
        //redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(sb.toString())));
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/checkandset.lua")));
        redisScript.setResultType(Boolean.class);
        return redisScript;
    }

}
