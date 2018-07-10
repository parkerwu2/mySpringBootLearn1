package com.wjz.springboot1.disruptor.park;

import lombok.Data;

/**
 * Created by jingzhi.wu on 2018/7/10.
 */
@Data
public class MyInParkingDataEvent {
    private String carLicense; // 车牌号
}
