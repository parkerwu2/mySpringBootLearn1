package com.wjz.springboot1.disruptor.park;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * Created by jingzhi.wu on 2018/7/10.
 */
public class MyParkingDataInDbHandler implements EventHandler<MyInParkingDataEvent> {
    @Override
    public void onEvent(MyInParkingDataEvent myInParkingDataEvent, long l, boolean b) throws Exception {
        long threadId = Thread.currentThread().getId(); // 获取当前线程id
        String carLicense = myInParkingDataEvent.getCarLicense(); // 获取车牌号
        System.out.println(String.format("Thread Id %s 保存 %s 到数据库中 ....", threadId, carLicense));
    }

//    @Override
//    public void onEvent(MyInParkingDataEvent myInParkingDataEvent) throws Exception {
//        this.onEvent(myInParkingDataEvent);
//    }
}
