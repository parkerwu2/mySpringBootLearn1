package com.wjz.springboot1;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jingzhi.wu on 2019/1/10.
 */
@RestController
@RequestMapping(value = "/apollo")
@Slf4j
public class ApolloController {
    @ApolloConfig
    private Config config; //inject config for namespace application

    @RequestMapping(value = "/get_config")
    public String getConfig(@RequestParam(value="key", required = true) String key) throws Exception{
        String defaultVal = "没有这个key";
        String publicNamespace = "99bill.bizHour";
        return config.getProperty(key, defaultVal);
//        Config privateConfig = ConfigService.getConfig("99bill.bizHour");
//       return privateConfig.getProperty("CutomerId-IndustryId-SystemId-MerchantId", null); // k1 = v3
    }

    //配置刷新

}
