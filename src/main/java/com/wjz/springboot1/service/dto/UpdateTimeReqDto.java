package com.wjz.springboot1.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by jingzhi.wu on 2018/4/24.
 */
@Data
public class UpdateTimeReqDto {
    @NotNull(message = "订单编号不能为空")
    private String orderNo;
    @NotNull(message = "新买家留言不能为空")
    private String newBuyerMessage;
}
