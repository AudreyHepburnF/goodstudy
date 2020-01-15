package com.goodstudy.interview.juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author congyaozhu
 * @date 2020-01-15 17:51
 * @description 阻塞队列版生产者消费者
 */

class MyResource{

    // 保证多线程环境下可见性
    private volatile boolean FLAG = true;

    // 原子Integer
    private AtomicInteger atomicInteger = new AtomicInteger();

    private BlockingQueue<String> blockingQueue = null;

    // 构造赋值
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println("初始化BlockingQueue的类：" + blockingQueue.getClass().getName());
    }

    public void myProducer(){

    }
}

public class ProdConsumerBlockQueueDemo {


}
