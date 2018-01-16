package com.wjz.springboot1.service.order;

import com.wjz.springboot1.persistence.dao.OrderPoMapper;
import com.wjz.springboot1.persistence.model.OrderPo;
import com.wjz.springboot1.persistence.model.OrderPoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jingzhi.wu on 2018/1/16.
 */
@Service
public class OrderService {
    @Autowired
    private OrderPoMapper orderPoMapper;

    public List<OrderPo> queryOrderByNo(String orderNo) {
        OrderPoExample example = new OrderPoExample();
        example.createCriteria().andOrderNoEqualTo(orderNo);
        return orderPoMapper.selectByExample(example);
    }
}
