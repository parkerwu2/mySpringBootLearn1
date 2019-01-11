package com.wjz.springboot1.service.order;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.RefreshPolicy;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.alicp.jetcache.anno.CreateCache;
import com.alicp.jetcache.embedded.LinkedHashMapCacheBuilder;
import com.wjz.springboot1.persistence.dao.OrderPoMapper;
import com.wjz.springboot1.persistence.model.OrderPo;
import com.wjz.springboot1.persistence.model.OrderPoExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by jingzhi.wu on 2019/1/11.
 */
@Service
@Slf4j
public class JetCacheOrderService {
    @Autowired
    private OrderPoMapper orderPoMapper;

    @CreateCache
    @CacheRefresh(timeUnit = TimeUnit.SECONDS,refresh = 60)
    private Cache<String, Long> orderSumCache;

//    @PostConstruct
//    public void init(){
//        RefreshPolicy policy = RefreshPolicy.newPolicy(1, TimeUnit.MINUTES)
//                .stopRefreshAfterLastAccess(30, TimeUnit.MINUTES);
//        orderSumCache = LinkedHashMapCacheBuilder
//                .createLinkedHashMapCacheBuilder()
//                .loader(key -> loadOrderSumFromDatabase(key))
//                .refreshPolicy(policy)
//                .buildCache();
//    }
//
//    public Long loadOrderSumFromDatabase(String key){
//
//    }

    @Cached(expire = 3600, cacheType = CacheType.BOTH, name="orderByNoCache")
    @CacheRefresh(refresh = 60, stopRefreshAfterLastAccess = 3600, timeUnit = TimeUnit.SECONDS)
    public List<OrderPo> queryOrderByNo(String orderNo) {
        log.info("jet cache queryOrderByNo={}", orderNo);
        OrderPoExample example = new OrderPoExample();
        example.createCriteria().andOrderNoEqualTo(orderNo);
        List<OrderPo> l= orderPoMapper.selectByExample(example);
        return l;
    }
}
