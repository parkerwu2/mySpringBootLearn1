package com.wjz.springboot1.service.dto;

import com.wjz.springboot1.persistence.model.OrderPo;
import com.wjz.springboot1.service.common.ApiBaseResponse;
import lombok.Data;

import java.util.List;

/**
 * Created by jingzhi.wu on 2018/1/16.
 */
@Data
public class QueryOrderByNoRespDto extends ApiBaseResponse {
    private List<OrderPo> orderPos;
}
