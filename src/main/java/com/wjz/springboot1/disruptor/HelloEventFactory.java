package com.wjz.springboot1.disruptor;
import com.lmax.disruptor.EventFactory;

/**
 * Created by jingzhi.wu on 2018/7/10.
 */
public class HelloEventFactory implements EventFactory<HelloEvent> {

    @Override
    public HelloEvent newInstance() {
        return new HelloEvent();
    }
}
