package com.wjz.springboot1.service.dto;

import com.github.pagehelper.PageInfo;
import com.wjz.springboot1.persistence.model.OrderPo;
import com.wjz.springboot1.service.common.ApiBaseResponse;
import lombok.Data;

/**
 * Created by jingzhi.wu on 2019/1/23.
 */
@Data
public class QueryOrderByNoRespDto2 extends ApiBaseResponse {
    PageInfo<OrderPo> pageInfo;
}
