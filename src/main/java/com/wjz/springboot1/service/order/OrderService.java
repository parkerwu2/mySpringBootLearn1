package com.wjz.springboot1.service.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjz.springboot1.persistence.dao.OrderPoMapper;
import com.wjz.springboot1.persistence.model.OrderPo;
import com.wjz.springboot1.persistence.model.OrderPoExample;
import com.wjz.springboot1.service.dto.QueryOrderByNoReqDto2;
import com.wjz.springboot1.service.dto.UpdateTimeReqDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jingzhi.wu on 2018/1/16.
 */
@Service
@Slf4j
public class OrderService {
    @Autowired
    private OrderPoMapper orderPoMapper;

    @Cacheable(value = "orderNoCache", key = "#orderNo")
    public List<OrderPo> queryOrderByNo(String orderNo) {
        log.info("queryOrderByNo={}", orderNo);
        OrderPoExample example = new OrderPoExample();
        example.createCriteria().andOrderNoEqualTo(orderNo);
        return orderPoMapper.selectByExample(example);
    }

    public PageInfo<OrderPo> queryOrderByNo(QueryOrderByNoReqDto2 queryOrderByNoReqDto2) {
        PageHelper.startPage(queryOrderByNoReqDto2.getPageNo(), queryOrderByNoReqDto2.getPageSize());
        OrderPoExample example = new OrderPoExample();
        example.createCriteria().andOrderNoEqualTo(queryOrderByNoReqDto2.getOrderNo());
        List<OrderPo> list = orderPoMapper.selectByExample(example);
        PageInfo<OrderPo> pageInfo = new PageInfo<>(list);
        return pageInfo;
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
