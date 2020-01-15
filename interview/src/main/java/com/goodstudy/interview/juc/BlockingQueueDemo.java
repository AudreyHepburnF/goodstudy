package com.goodstudy.interview.juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author congyaozhu
 * @date 2020-01-15 10:21
 * @description 阻塞队列 demo
 *  特点：
 *      1. 当阻塞队列为空时,从队列中获取元素的操作将会被阻塞.
 *      2. 当阻塞队列已满时,往队列中添加元素的操作将会被阻塞.
 *  优点：多线程环境下，不需要手动控制线程挂起唤醒操作
 *  架构：属于Collection集合下面，与List接口同级。
 *  种类分析：
 *      ☆1. ArrayBlockingQueue: 由数组结构组成的有界阻塞队列.
 *      ☆2. LinkedBlockingDeque: 由链表结构组成的有界(但大小默认值Integer>MAX_VALUE)阻塞队列.
 *          ps：需要注意默认值，不设置的情况下因数值过大(2147483647)，相当于无界队列
 *      3. PriorityBlockingQueue:支持优先级排序的无界阻塞队列.
 *      4. DelayQueue: 使用优先级队列实现的延迟无界阻塞队列.
 *      ☆5. SynchronousQueue:不存储元素的阻塞队列,也即是单个元素的队列.
 *      6. LinkedTransferQueue:由链表结构组成的无界阻塞队列.
 *      7. LinkedBlockingDeque:由了解结构组成的双向阻塞队列.
 *
 *  常用方法：
 *      1. 会抛异常（超出默认值）
 *          add
 *          remove
 *          element
 *      2. 返回true/false(超出默认值)
 *          offer(e)
 *          poll()
 *          peek()
 *      3. 阻塞(超出默认值)
 *          put()
 *          take()
 *      4. 超时(超出默认值)
 *          offer(e,timeout,timeunit)
 *          poll(timeout,timeunit)
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        // 初始化一个同步队列
        // 同步队列 ： 不存储元素。生产一个，消费一个。
        //  ecg : 私人订制
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1");
                blockingQueue.put("1");

                System.out.println(Thread.currentThread().getName() + "\t put 2");
                blockingQueue.put("2");

                System.out.println(Thread.currentThread().getName() + "\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } , "AAA").start();

        new Thread(() -> {
            try {
                try{ TimeUnit.SECONDS.sleep(5); } catch ( InterruptedException e ) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\t " + blockingQueue.take());

                try{ TimeUnit.SECONDS.sleep(5); } catch ( InterruptedException e ) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\t " + blockingQueue.take());

                try{ TimeUnit.SECONDS.sleep(5); } catch ( InterruptedException e ) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName() + "\t " + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } , "BBB").start();
    }
}
