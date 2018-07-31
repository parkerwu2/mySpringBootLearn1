package com.wjz.springboot1.service.jms;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jingzhi.wu on 2018/3/20.
 */
@Component
public class Consumer {
    private AtomicInteger cnt = new AtomicInteger(1);
    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "mytest.queue")
    public void receiveQueue(String text) {
        System.out.println("msg 抢购成功者是:" + text + ", 成功人数:" + cnt.getAndIncrement());
    }
}
