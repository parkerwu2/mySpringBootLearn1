package com.wjz.springboot1;

import com.wjz.springboot1.persistence.model.OrderPo;
import com.wjz.springboot1.service.dto.QueryOrderByNoRespDto;
import com.wjz.springboot1.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jingzhi.wu on 2017/12/22.
 */
@RestController
@RequestMapping(value = "/order")
public class HelloController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/")
    public String hello() throws Exception{
        if (true){
            throw new RuntimeException("i am wrong");
        }
        return "hello";
    }

    @RequestMapping(value = "/queryOrderByNo")
    public QueryOrderByNoRespDto queryOrderByNo(@RequestParam(value="orderNo", required = true) String orderNo) {
        QueryOrderByNoRespDto dto = new QueryOrderByNoRespDto();
        if (!StringUtils.isEmpty(orderNo)) {
            List<OrderPo> orderPos = orderService.queryOrderByNo(orderNo);
            dto.setOrderPos(orderPos);
        } else {
            dto.setCode("-1");
            dto.setMessage("订单编号不能为空");
        }
        return dto;
    }

}
