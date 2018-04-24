package com.wjz.springboot1.service.order;

import com.wjz.springboot1.persistence.dao.OrderPoMapper;
import com.wjz.springboot1.persistence.model.OrderPo;
import com.wjz.springboot1.persistence.model.OrderPoExample;
import com.wjz.springboot1.service.dto.UpdateTimeReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor=Exception.class)
    public void updateBuyerMessage(UpdateTimeReqDto dto) throws Exception {
        OrderPo order = new OrderPo();
        order.setBuyerMessage(dto.getNewBuyerMessage());
        OrderPoExample example = new OrderPoExample();
        example.createCriteria().andOrderNoEqualTo(dto.getOrderNo());
        orderPoMapper.updateByExampleSelective(order, example);

//        throw new Exception("human made error");
    }
}
