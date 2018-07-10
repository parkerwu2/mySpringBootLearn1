package com.wjz.springboot1.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jingzhi.wu on 2018/7/10.
 */
public class DisruptorMain {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        WaitStrategy blockingWaitStrategy = new BlockingWaitStrategy();
        WaitStrategy sleepingWaitStrategy = new SleepingWaitStrategy();
        WaitStrategy yieldWaitStrategy = new YieldingWaitStrategy();

        EventFactory<HelloEvent> eventEventFactory = new HelloEventFactory();
        int ringBufferSize = 1024*1024;
        Disruptor<HelloEvent> disruptor = new Disruptor<HelloEvent>(eventEventFactory,
                ringBufferSize, executor, ProducerType.SINGLE, blockingWaitStrategy);

        EventHandler<HelloEvent> eventHandler=new HelloEventHandler();
        disruptor.handleEventsWith(eventHandler);
        disruptor.start();

        RingBuffer<HelloEvent> ringBuffer = disruptor.getRingBuffer();
        long sequence = ringBuffer.next();
        try {
            HelloEvent event = ringBuffer.get(sequence);
            event.setValue("井底之蛙说：Hello world!" + sequence);
        } catch (Exception e){

        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
