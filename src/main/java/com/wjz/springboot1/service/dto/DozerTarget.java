package com.wjz.springboot1.service.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by jingzhi.wu on 2019/1/15.
 */
@Data
public class DozerTarget {
    private Long id;
    private List<QueryOrderByNoReqDozerDto> queryOrderByNoReqDtos;
    private UpdateTimeReqDto updateTimeReqDto;
}
