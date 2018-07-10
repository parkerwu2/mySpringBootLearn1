package com.wjz.springboot1.disruptor;


import com.lmax.disruptor.EventHandler;

/**
 * Created by jingzhi.wu on 2018/7/10.
 */
public class HelloEventHandler implements EventHandler<HelloEvent> {

    @Override
    public void onEvent(HelloEvent helloEvent, long l, boolean b) throws Exception {
        System.out.println(helloEvent.getValue());
    }
}
