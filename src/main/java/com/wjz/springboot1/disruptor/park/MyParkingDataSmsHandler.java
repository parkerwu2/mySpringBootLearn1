package com.wjz.springboot1.disruptor.park;

import com.lmax.disruptor.EventHandler;

/**
 * Created by jingzhi.wu on 2018/7/10.
 */
public class MyParkingDataSmsHandler implements EventHandler<MyInParkingDataEvent> {
    @Override
    public void onEvent(MyInParkingDataEvent myInParkingDataEvent, long l, boolean b) throws Exception {
        long threadId = Thread.currentThread().getId(); // 获取当前线程id
        String carLicense = myInParkingDataEvent.getCarLicense(); // 获取车牌号
        System.out.println(String.format("Thread Id %s 给  %s 的车主发送一条短信，并告知他计费开始了 ....", threadId, carLicense));
    }
}
