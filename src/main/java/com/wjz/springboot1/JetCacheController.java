package com.wjz.springboot1;

import com.wjz.springboot1.persistence.model.OrderPo;
import com.wjz.springboot1.service.dto.QueryOrderByNoRespDto;
import com.wjz.springboot1.service.order.JetCacheOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jingzhi.wu on 2019/1/11.
 */
@RestController
@RequestMapping(value = "/jetcache")
@Slf4j
public class JetCacheController {
    @Autowired
    private JetCacheOrderService jetCacheOrderService;

    @RequestMapping(value = "/queryOrderByNo")
    public QueryOrderByNoRespDto queryOrderByNo(@RequestParam(value="orderNo", required = true) String orderNo) {
        log.info("queryOrderByNo start={}", orderNo);
        QueryOrderByNoRespDto dto = new QueryOrderByNoRespDto();
        if (!StringUtils.isEmpty(orderNo)) {
            List<OrderPo> orderPos = jetCacheOrderService.queryOrderByNo(orderNo);
            dto.setOrderPos(orderPos);
        } else {
            dto.setCode("-1");
            dto.setMessage("订单编号不能为空");
        }
        return dto;
    }
}
